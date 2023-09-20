/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - Bug 397429
 *******************************************************************************/
package org.eclipse.sme.frsl.cs2as;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.base.scoping.AbstractJavaClassScope;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.CSI;
import org.eclipse.ocl.xtext.base.utilities.CSI2ASMapping;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.ocl.xtext.oclinecore.cs2as.OCLinEcoreCS2AS;
import org.eclipse.sme.frsl.utilities.FRSLUtil;
import org.eclipse.sme.frslcs.util.FRSLCSVisitor;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;

public class FRSLCS2AS extends OCLinEcoreCS2AS{
	
	/**
	 * The AS resource mapped by this CS2AS.
	 */
	//protected final @NonNull Resource frslASResource;

	/**
	 * Mapping of FRSLCS elements (use case model) to the resulting FRSL elements.
	 */
	protected final Map<ElementCS, EObject> frslCS2ASMap = new HashMap<ElementCS, EObject>();
	
	/**
	 * Mapping of name of FRSL elements to the corresponding Pivot element.
	 */
	protected final Map<String, Element> frslCS2Pivot = new HashMap<String, Element>();
	
	/**
	 * Mapping of elements of the presnapshot to ones of the postsnapshot
	 */
	protected final Map<EObject, EObject> frslCSPre2Post = new HashMap<EObject, EObject>();
	
	/**
	 * Maintaining all the TypedTypeRefCS elements of the FrslModel.
	 */
	protected final List<TypedTypeRefCS> allTypedTypeRefCS = new ArrayList<TypedTypeRefCS>();


	public FRSLCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull BaseCSResource csResource, @NonNull ASResource asResource) {
		super(environmentFactory, csResource, asResource);
		
		//this.frslASResource = frslASResource;
		
//		ILog log = Platform.getLog(FRSLCS2AS.class);		
//		IStatus logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2AS **** asResource=" + asResource);
//		log.log(logMsg);
	}

	@Override
	protected @NonNull FRSLCSVisitor<Continuation<?>> createContainmentVisitor(@NonNull CS2ASConversion converter) {
		return new FRSLCSContainmentVisitor(converter);
	}

	@Override
	protected @NonNull FRSLCSVisitor<Element> createLeft2RightVisitor(@NonNull CS2ASConversion converter) {
		return new FRSLCSLeft2RightVisitor(converter);
	}

	@Override
	protected @NonNull FRSLCSVisitor<Continuation<?>> createPostOrderVisitor(@NonNull CS2ASConversion converter) {
		return new FRSLCSPostOrderVisitor(converter);
	}

	@Override
	protected @NonNull FRSLCSVisitor<Continuation<?>> createPreOrderVisitor(@NonNull CS2ASConversion converter) {
		return new FRSLCSPreOrderVisitor(converter);
	}
	
//	public @NonNull Resource getFrslASResource() {
//		return this.frslASResource;
//	}
	
	public CSI2ASMapping getCsi2asMapping() {
		return csi2asMapping;
	}
	
	public EObject getFrslElementAS(@NonNull ElementCS frslElementCS) {
		return this.frslCS2ASMap.get(frslElementCS);
	}
	
	public ElementCS getFrslElementCS(@NonNull EObject frslElement) {
		for(ElementCS elementCS: this.frslCS2ASMap.keySet() ) {
			if (this.frslCS2ASMap.get(elementCS).equals(frslElement) ) {
				return elementCS;
			}
		}
		return null;
	}
	
	public Element getPivot(@NonNull String frslMetaConceptCS_ElemName) {
		return this.frslCS2Pivot.get(frslMetaConceptCS_ElemName);
	}
	
	public EObject getPostElement(@NonNull EObject preSnapshotElement) {
		return this.frslCSPre2Post.get(preSnapshotElement);
	}

	
	@Override
	public synchronized void update(@NonNull IDiagnosticConsumer diagnosticsConsumer) {
//		ASResource asResource1 = csi2asMapping.getASResource(csResource);
//		ILog log = Platform.getLog(CS2AS.class);
//		Status status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: CS2AS$update asResource1 SIZE = ****" + (asResource1==null?0:asResource1.getContents().size()));
//		log.log(status);

		//		printDiagnostic("CS2AS.update start", false, 0);
		@SuppressWarnings("unused") Map<CSI, Element> oldCSI2AS = csi2asMapping.getMapping();
		@SuppressWarnings("unused") Set<CSI> newCSIs = csi2asMapping.computeCSIs(csResource);
		//		System.out.println("==========================================================================");
		//		for (Resource csResource : csResources) {
		//			System.out.println("CS " + csResource.getClass().getName() + "@" + csResource.hashCode() + " " + csResource.getURI());
		//		}
		
		//hanhdd_begin
		
		//CS2ASConversion conversion = createConversion(diagnosticsConsumer, csResource);
		//conversion.update(csResource);
		
		FRSLCS2ASConversion frslConverter = (FRSLCS2ASConversion) createConversion(diagnosticsConsumer, csResource);
		frslConverter.update(csResource);
		
		//hanhdd_end
		
		//		System.out.println("---------------------------------------------------------------------------");
		//		Collection<? extends Resource> pivotResources = cs2asResourceMap.values();
		//		for (Entry<? extends Resource, ? extends Resource> entry : cs2asResourceMap.entrySet()) {
		//			Resource csResource = entry.getKey();
		//			Resource asResource = entry.getValue();
		//			System.out.println("CS " + csResource.getClass().getName() + "@" + csResource.hashCode() + " => " + asResource.getClass().getName() + "@" + asResource.hashCode());
		//		}
		/*		Set<String> deadCSIs = new HashSet<String>(oldCSI2AS.keySet());
		deadCSIs.removeAll(newCSIs);
		for (String deadCSI : deadCSIs) {
			Element deadPivot = oldCSI2AS.get(deadCSI);	// WIP
//			metamodelManager.kill(deadPivot);
		} */
		Map<BaseCSResource, ASResource> cs2asResourceMap = new HashMap<BaseCSResource, ASResource>();
		ASResource asResource = csi2asMapping.getASResource(csResource);		
		cs2asResourceMap.put(csResource, asResource);
		AbstractJavaClassScope javaClassScope = AbstractJavaClassScope.findAdapter(csResource);
		if (javaClassScope != null) {
			javaClassScope.installContents(csResource);
		}

		frslConverter.garbageCollect(cs2asResourceMap);
		
		csi2asMapping.update();
		//		printDiagnostic("CS2AS.update end", false, 0);
	}
	
	@Override
	protected@NonNull  CS2ASConversion createConversion(@NonNull IDiagnosticConsumer diagnosticsConsumer, @NonNull BaseCSResource csResource) {
		return new FRSLCS2ASConversion(this, diagnosticsConsumer);
	}

	
}
