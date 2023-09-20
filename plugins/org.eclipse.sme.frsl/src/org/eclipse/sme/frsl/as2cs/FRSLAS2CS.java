/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.sme.frsl.as2cs;

import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.base.as2cs.AS2CS;
import org.eclipse.ocl.xtext.base.as2cs.AS2CSConversion;
import org.eclipse.ocl.xtext.base.as2cs.BaseDeclarationVisitor;
import org.eclipse.ocl.xtext.base.as2cs.BaseReferenceVisitor;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.essentialocl.as2cs.EssentialOCLAS2CS;
import org.eclipse.ocl.xtext.essentialocl.as2cs.EssentialOCLExpressionVisitor;
import org.eclipse.ocl.xtext.oclinecore.as2cs.OCLinEcoreAS2CS;
import org.eclipse.sme.frsl.as2cs.FRSLDeclarationVisitor;

public class FRSLAS2CS extends OCLinEcoreAS2CS
{
	private static final class Factory extends AbstractFactory
	{
		private static AS2CS.@NonNull Factory INSTANCE = new Factory();

		@Override
		public @NonNull FRSLDeclarationVisitor createDeclarationVisitor(@NonNull AS2CSConversion converter) {
			return new FRSLDeclarationVisitor(converter);
		}

		@Override
		public @NonNull EssentialOCLExpressionVisitor createExpressionVisitor(@NonNull AS2CSConversion converter, @Nullable Namespace scope) {
			return new EssentialOCLExpressionVisitor(converter, scope);
		}

		@SuppressWarnings("null")
		@Override
		public @NonNull EClass @NonNull [] getEClasses() {
			return new EClass @NonNull [] {
				PivotPackage.Literals.ANNOTATION,
				PivotPackage.Literals.CONSTRAINT,
				PivotPackage.Literals.EXPRESSION_IN_OCL
			};
		}
	}

	public FRSLAS2CS(@NonNull Map<@NonNull ? extends BaseCSResource, @NonNull ? extends ASResource> cs2asResourceMap, @NonNull EnvironmentFactoryInternal environmentFactory) {
		super(cs2asResourceMap, environmentFactory);
		
		//hanhdd
		ILog log = Platform.getLog(getClass());
		IStatus status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "FRSLAS2CS");		
		log.log(status);

		
		
		addFactory(Factory.INSTANCE);
		for (Resource csResource : cs2asResourceMap.keySet()) {
			assert csResource != null;
			environmentFactory.adapt(csResource);
		}
	}

	@Override
	public @NonNull BaseDeclarationVisitor createDefaultDeclarationVisitor(@NonNull AS2CSConversion converter) {
		return new FRSLDeclarationVisitor(converter);
	}

	@Override
	public @NonNull BaseReferenceVisitor createDefaultExpressionVisitor(@NonNull AS2CSConversion conversion) {
		return new EssentialOCLExpressionVisitor(conversion, null);
	}
}