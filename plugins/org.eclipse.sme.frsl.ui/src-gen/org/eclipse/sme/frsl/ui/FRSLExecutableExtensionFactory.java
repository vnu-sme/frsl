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
package org.eclipse.sme.frsl.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.eclipse.sme.frsl.ui.internal.FRSLActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass.
 */
public class FRSLExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FRSLActivator.getInstance().getBundle();
	}

	@Override
	protected Injector getInjector() {
		return FRSLActivator.getInstance().getInjector(FRSLActivator.ORG_ECLIPSE_SME_FRSL_FRSL);
	}

}
