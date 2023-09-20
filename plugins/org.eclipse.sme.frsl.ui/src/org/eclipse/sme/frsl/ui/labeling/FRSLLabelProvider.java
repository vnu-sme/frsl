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
package org.eclipse.sme.frsl.ui.labeling;

import org.eclipse.sme.frslcs.*;
import org.eclipse.ocl.xtext.oclinecore.ui.labeling.OCLinEcoreLabelProvider;

import com.google.inject.Inject;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#label-provider
 */
public class FRSLLabelProvider extends OCLinEcoreLabelProvider {

	@Inject
	public FRSLLabelProvider(org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}
	
	protected String image(UsecaseCS uc) {
		return "/org.eclipse.sme.frsl.ui/icons/full/usecase.gif";
	}
	protected String image(ActorCS actor) {
		return "/org.eclipse.sme.frsl.ui/icons/full/actor.gif";
	}
	protected String image(StepCS step) {
		return "/org.eclipse.sme.frsl.ui/icons/full/step.gif";
	}
	protected String image(SnapshotPatternCS snapshot) {
		return "/org.eclipse.sme.frsl.ui/icons/full/snapshot.gif";
	}
}
