/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.sme.frsl.utilities;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

//import org.eclipse.core.runtime.ILog;
//import org.eclipse.core.runtime.IStatus;
//import org.eclipse.core.runtime.Platform;
//import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.xtext.base.as2cs.AS2CS;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.oclinecore.utilities.OCLinEcoreCSResource;
import org.eclipse.sme.frsl.as2cs.FRSLAS2CS;
import org.eclipse.sme.frsl.cs2as.FRSLCS2AS;

public class FRSLCSResource extends OCLinEcoreCSResource
{
	@Override
	public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
		return super.basicSetResourceSet(resourceSet, notifications);
	}

	@Override
	public @NonNull AS2CS createAS2CS(@NonNull Map<@NonNull ? extends BaseCSResource, @NonNull ? extends ASResource> cs2asResourceMap,
			@NonNull EnvironmentFactoryInternal environmentFactory) {
		return new FRSLAS2CS(cs2asResourceMap, environmentFactory);
	}

	@Override
	public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
//		Status status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSResource ****" + asResource.getContents().size());
//		ILog log = Platform.getLog(FRSLCSResource.class);
//		log.log(status);
		
		//@NonNull Resource frslASResource;
		 
		//URI frslasURI = ClassUtil.nonNullState(getURI()).trimFileExtension().appendFileExtension("frslas");
				
		//ResourceSet frslResourceSet = environmentFactory.getMetamodelManager().getASResourceSet();
		
		//frslASResource = frslResourceSet.createResource(frslasURI);
		
		return new FRSLCS2AS(environmentFactory, this, asResource);
	}

	@Override
	public void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (getContents().size() > 0) {
			super.doSave(outputStream, options);	// Avoid NPE or ISE from XtextResource
		}
	}

	@Override
	public @NonNull String getASContentType() {
		return FRSLASResourceFactory.FRSL_CONTENT_TYPE;
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return FRSLASResourceFactory.getInstance();
	}

	@Override
	public @NonNull String getEditorName() {
		return "FRSL";
	}
	
	public EObject getFrslElementAS(@NonNull ElementCS frslElementCS) {		
		return ((FRSLCS2AS) this.getCS2AS()).getFrslElementAS(frslElementCS);
	}

//	public @NonNull Resource getFrslASResource() {
//		FRSLCS2AS frslcs2as = (FRSLCS2AS) this.getCS2AS();
//		Resource frslASResource = frslcs2as.getFrslASResource();
//		return frslASResource;
//	}

//	@Override
//	public @NonNull URI getASURI(@NonNull URI csURI) {
//		return csURI.appendFileExtension(PivotConstants.OCL_AS_FILE_EXTENSION);
//	}

}