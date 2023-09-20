/*******************************************************************************
 * Copyright (c) 2021 the VNU-SME lab.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Duc-Hanh Dang and the VNU-SME lab
 *******************************************************************************/
package org.eclipse.sme.frsl.cs2as;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.sme.frslcs.util.AbstractFrslCSLeft2RightVisitor;

public class FRSLCSLeft2RightVisitor extends AbstractFrslCSLeft2RightVisitor
{
	public FRSLCSLeft2RightVisitor(@NonNull CS2ASConversion context) {
		super(context);
	}
	
	@Override
	public @Nullable Element visitActionCS(org.eclipse.sme.frslcs.@NonNull ActionCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitActorActionCS(org.eclipse.sme.frslcs.@NonNull ActorActionCS csElement) {
		return visitActionCS(csElement);
	}

	@Override
	public @Nullable Element visitActorCS(org.eclipse.sme.frslcs.@NonNull ActorCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitAltFlowCS(org.eclipse.sme.frslcs.@NonNull AltFlowCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitAssociationCS(org.eclipse.sme.frslcs.@NonNull AssociationCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitConstraintCS(org.eclipse.sme.frslcs.@NonNull ConstraintCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitExtendCS(org.eclipse.sme.frslcs.@NonNull ExtendCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitExtensionPointCS(org.eclipse.sme.frslcs.@NonNull ExtensionPointCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitFrslModelCS(org.eclipse.sme.frslcs.@NonNull FrslModelCS csElement) {
		return visitRootCS(csElement);
	}

	@Override
	public @Nullable Element visitObjVarCS(org.eclipse.sme.frslcs.@NonNull ObjVarCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitRejoinStepCS(org.eclipse.sme.frslcs.@NonNull RejoinStepCS csElement) {
		return visitStepCS(csElement);
	}

	@Override
	public @Nullable Element visitSnapshotPatternCS(org.eclipse.sme.frslcs.@NonNull SnapshotPatternCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitStepCS(org.eclipse.sme.frslcs.@NonNull StepCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitSystemActionCS(org.eclipse.sme.frslcs.@NonNull SystemActionCS csElement) {
		return visitActionCS(csElement);
	}

	@Override
	public @Nullable Element visitUCStepCS(org.eclipse.sme.frslcs.@NonNull UCStepCS csElement) {
		return visitStepCS(csElement);
	}

	@Override
	public @Nullable Element visitUsecaseCS(org.eclipse.sme.frslcs.@NonNull UsecaseCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitUsecasePostconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePostconditionCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitUsecasePreconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePreconditionCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Element visitVarLinkCS(org.eclipse.sme.frslcs.@NonNull VarLinkCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

}