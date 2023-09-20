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
package org.eclipse.sme.frsl;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sme.frsl.utilities.FRSLASResourceFactory;
import org.eclipse.sme.frslcs.FrslCSPackage;

import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
public class FRSLStandaloneSetup extends FRSLStandaloneSetupGenerated
{
	private static Injector injector = null;

	public static void doSetup() {
		if (injector == null) {
			new FRSLStandaloneSetup().createInjectorAndDoEMFRegistration();
		}
	}

	public static void doTearDown() {
		injector = null;
	}

	public static void init() {
		FRSLASResourceFactory.getInstance();
//		FRSLAS2CS.FACTORY.getClass();
		EPackage.Registry.INSTANCE.put(FrslCSPackage.eNS_URI, FrslCSPackage.eINSTANCE);
	}

	/**
	 * Return the Injector for this plugin.
	 */
	public static final Injector getInjector() {
		return injector;
	}

	@Override
	public Injector createInjector() {
		init();
		injector = super.createInjector();
		return injector;
	}
}