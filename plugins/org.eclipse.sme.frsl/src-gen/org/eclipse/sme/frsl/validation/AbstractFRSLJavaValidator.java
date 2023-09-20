/*******************************************************************************
 * Copyright (c) 2021 Duc-Hanh Dang and the VNU-SME lab.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Duc-Hanh Dang and the VNU-SME lab
 *******************************************************************************/
package org.eclipse.sme.frsl.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators= {org.eclipse.xtext.validation.ImportUriValidator.class})
public class AbstractFRSLJavaValidator extends org.eclipse.ocl.xtext.oclinecore.validation.OCLinEcoreJavaValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.xtext.org/sme/FRSLCS"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/ocl/2015/BaseCS"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/ocl/2015/OCLinEcoreCS"));
	    result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/ocl/2015/EssentialOCLCS"));
		return result;
	}

}
