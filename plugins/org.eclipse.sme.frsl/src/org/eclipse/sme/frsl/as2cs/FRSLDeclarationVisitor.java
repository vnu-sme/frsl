/*******************************************************************************
 * Copyright (c) 2021 the VNU-SME lab.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Duc-Hanh Dang and the VNU-SME lab
 *******************************************************************************/
package org.eclipse.sme.frsl.as2cs;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.internal.utilities.AbstractConversion.Predicate;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.xtext.base.as2cs.AS2CSConversion;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.oclinecore.as2cs.OCLinEcoreDeclarationVisitor;
import org.eclipse.sme.frslcs.FrslModelCS;
import org.eclipse.sme.frslcs.FrslCSPackage;

public class FRSLDeclarationVisitor extends OCLinEcoreDeclarationVisitor {
	private IStatus status;
	private ILog log = Platform.getLog(getClass());
	
	private static final @NonNull Predicate<org.eclipse.ocl.pivot.Package> nonImplicitPackageFilter =
			new Predicate<org.eclipse.ocl.pivot.Package>()
	{
		@Override
		public boolean filter(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
			return !PivotUtilInternal.isImplicitPackage(asPackage);
		}
	};

	public FRSLDeclarationVisitor(@NonNull AS2CSConversion context) {
		super(context);
		
		log = Platform.getLog(getClass());
		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "FRSLDEclarationVisitor");		
		log.log(status);

	}

	@Override
	public void postProcess(@NonNull BaseCSResource csResource, @NonNull Map<@NonNull Namespace, @NonNull List<@NonNull String>> importedNamespaces) {
		EObject eObject = csResource.getContents().get(0);
		if (eObject instanceof FrslModelCS) {
			context.createImports((FrslModelCS) eObject, importedNamespaces);
		}
	}

	@Override
	public ElementCS visitModel(@NonNull Model object) {
		FrslModelCS csElement = context.refreshElement(FrslModelCS.class, FrslCSPackage.Literals.FRSL_MODEL_CS, object);
		//FIXME: @hanhdd How to visit the domain model and use cases of the frslModel
		
		log = Platform.getLog(getClass());
		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "FRSLDEclarationVisitor");		
		log.log(status);
		
		return csElement;
	}
}