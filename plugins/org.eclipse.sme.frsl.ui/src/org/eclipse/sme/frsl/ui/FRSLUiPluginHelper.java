/*******************************************************************************
 * Copyright (c) 2021 the VNU-SME lab
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     The VNU-SME lab
 *******************************************************************************/
package org.eclipse.sme.frsl.ui;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.sme.frsl.ui.internal.FRSLActivator;

public class FRSLUiPluginHelper extends EMFPlugin.InternalHelper
{
	public static final FRSLUiPluginHelper INSTANCE = new FRSLUiPluginHelper(FRSLActivator.getInstance());

	private FRSLUiPluginHelper(Plugin plugin) {
		super(plugin);
	}

	public Status createErrorStatus(Exception e) {
		return new Status(Status.ERROR, getSymbolicName(), e.getMessage(), e);
	}
}
