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
package org.eclipse.sme.frsl.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import java.util.List;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.ocl.xtext.oclinecore.services.OCLinEcoreGrammarAccess;
import org.eclipse.ocl.xtext.essentialocl.services.EssentialOCLGrammarAccess;
import org.eclipse.ocl.xtext.base.services.BaseGrammarAccess;

@Singleton
public class FRSLGrammarAccess extends AbstractGrammarElementFinder {


	public class FrslModelCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.FrslModelCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cDomainModelAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cDomainModelTopLevelCSParserRuleCall_0_0 = (RuleCall)cDomainModelAssignment_0.eContents().get(0);
		private final Assignment cAssocsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cAssocsAssociationCSParserRuleCall_1_0 = (RuleCall)cAssocsAssignment_1.eContents().get(0);
		private final Assignment cActorsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cActorsActorCSParserRuleCall_2_0 = (RuleCall)cActorsAssignment_2.eContents().get(0);
		private final Assignment cUsecasesAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cUsecasesUsecaseCSParserRuleCall_3_0 = (RuleCall)cUsecasesAssignment_3.eContents().get(0);
		private final Assignment cUcExtendsAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cUcExtendsUcExtendCSParserRuleCall_4_0 = (RuleCall)cUcExtendsAssignment_4.eContents().get(0);

		////generate frsl "http://www.eclipse.org/sme/frsl/Frsl"
		//FrslModelCS:
		//	domainModel=TopLevelCS
		//	assocs+=AssociationCS*
		//	actors+=ActorCS*
		//	usecases+=UsecaseCS*
		//	ucExtends+=UcExtendCS*;
		@Override public ParserRule getRule() { return rule; }

		//domainModel=TopLevelCS
		//assocs+=AssociationCS*
		//actors+=ActorCS*
		//usecases+=UsecaseCS*
		//ucExtends+=UcExtendCS*
		public Group getGroup() { return cGroup; }

		//domainModel=TopLevelCS
		public Assignment getDomainModelAssignment_0() { return cDomainModelAssignment_0; }

		//TopLevelCS
		public RuleCall getDomainModelTopLevelCSParserRuleCall_0_0() { return cDomainModelTopLevelCSParserRuleCall_0_0; }

		//assocs+=AssociationCS*
		public Assignment getAssocsAssignment_1() { return cAssocsAssignment_1; }

		//AssociationCS
		public RuleCall getAssocsAssociationCSParserRuleCall_1_0() { return cAssocsAssociationCSParserRuleCall_1_0; }

		//actors+=ActorCS*
		public Assignment getActorsAssignment_2() { return cActorsAssignment_2; }

		//ActorCS
		public RuleCall getActorsActorCSParserRuleCall_2_0() { return cActorsActorCSParserRuleCall_2_0; }

		//usecases+=UsecaseCS*
		public Assignment getUsecasesAssignment_3() { return cUsecasesAssignment_3; }

		//UsecaseCS
		public RuleCall getUsecasesUsecaseCSParserRuleCall_3_0() { return cUsecasesUsecaseCSParserRuleCall_3_0; }

		//ucExtends+=UcExtendCS*
		public Assignment getUcExtendsAssignment_4() { return cUcExtendsAssignment_4; }

		//UcExtendCS
		public RuleCall getUcExtendsUcExtendCSParserRuleCall_4_0() { return cUcExtendsUcExtendCSParserRuleCall_4_0; }
	}

	public class UsecaseCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.UsecaseCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cUsecaseKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDescriptionKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDescriptionAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_2_2_0 = (RuleCall)cDescriptionAssignment_2_2.eContents().get(0);
		private final Keyword cPrimaryActorKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Keyword cEqualsSignKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cPrimaryActorAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cPrimaryActorActorRefCSParserRuleCall_5_0 = (RuleCall)cPrimaryActorAssignment_5.eContents().get(0);
		private final Group cGroup_6 = (Group)cGroup.eContents().get(6);
		private final Keyword cSecondaryActorsKeyword_6_0 = (Keyword)cGroup_6.eContents().get(0);
		private final Keyword cEqualsSignKeyword_6_1 = (Keyword)cGroup_6.eContents().get(1);
		private final Keyword cLeftCurlyBracketKeyword_6_2 = (Keyword)cGroup_6.eContents().get(2);
		private final Assignment cSecondaryActorsAssignment_6_3 = (Assignment)cGroup_6.eContents().get(3);
		private final RuleCall cSecondaryActorsActorRefCSParserRuleCall_6_3_0 = (RuleCall)cSecondaryActorsAssignment_6_3.eContents().get(0);
		private final Group cGroup_6_4 = (Group)cGroup_6.eContents().get(4);
		private final Keyword cCommaKeyword_6_4_0 = (Keyword)cGroup_6_4.eContents().get(0);
		private final Assignment cSecondaryActorsAssignment_6_4_1 = (Assignment)cGroup_6_4.eContents().get(1);
		private final RuleCall cSecondaryActorsActorRefCSParserRuleCall_6_4_1_0 = (RuleCall)cSecondaryActorsAssignment_6_4_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_6_5 = (Keyword)cGroup_6.eContents().get(5);
		private final Assignment cPreconditionAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cPreconditionUsecasePreconditionCSParserRuleCall_7_0 = (RuleCall)cPreconditionAssignment_7.eContents().get(0);
		private final Assignment cPostconditionAssignment_8 = (Assignment)cGroup.eContents().get(8);
		private final RuleCall cPostconditionUsecasePostconditionCSParserRuleCall_8_0 = (RuleCall)cPostconditionAssignment_8.eContents().get(0);
		private final Assignment cFirstStepAssignment_9 = (Assignment)cGroup.eContents().get(9);
		private final RuleCall cFirstStepStepCSParserRuleCall_9_0 = (RuleCall)cFirstStepAssignment_9.eContents().get(0);
		private final Assignment cExtPointsAssignment_10 = (Assignment)cGroup.eContents().get(10);
		private final RuleCall cExtPointsExtensionPointCSParserRuleCall_10_0 = (RuleCall)cExtPointsAssignment_10.eContents().get(0);
		private final Keyword cEndKeyword_11 = (Keyword)cGroup.eContents().get(11);

		//UsecaseCS:
		//	'usecase' name=UnrestrictedName ('description' '=' description=StringLiteral)?
		//	'primaryActor' '=' primaryActor=ActorRefCS ('secondaryActors' '=' '{' secondaryActors+=ActorRefCS (','
		//	secondaryActors+=ActorRefCS)* '}')?
		//	precondition=UsecasePreconditionCS?
		//	postcondition=UsecasePostconditionCS?
		//	firstStep=StepCS
		//	extPoints+=ExtensionPointCS*
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'usecase' name=UnrestrictedName ('description' '=' description=StringLiteral)?
		//'primaryActor' '=' primaryActor=ActorRefCS ('secondaryActors' '=' '{' secondaryActors+=ActorRefCS (','
		//secondaryActors+=ActorRefCS)* '}')?
		//precondition=UsecasePreconditionCS?
		//postcondition=UsecasePostconditionCS?
		//firstStep=StepCS
		//extPoints+=ExtensionPointCS*
		//'end'
		public Group getGroup() { return cGroup; }

		//'usecase'
		public Keyword getUsecaseKeyword_0() { return cUsecaseKeyword_0; }

		//name=UnrestrictedName
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_2() { return cGroup_2; }

		//'description'
		public Keyword getDescriptionKeyword_2_0() { return cDescriptionKeyword_2_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_2_2() { return cDescriptionAssignment_2_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_2_2_0() { return cDescriptionStringLiteralParserRuleCall_2_2_0; }

		//'primaryActor'
		public Keyword getPrimaryActorKeyword_3() { return cPrimaryActorKeyword_3; }

		//'='
		public Keyword getEqualsSignKeyword_4() { return cEqualsSignKeyword_4; }

		//primaryActor=ActorRefCS
		public Assignment getPrimaryActorAssignment_5() { return cPrimaryActorAssignment_5; }

		//ActorRefCS
		public RuleCall getPrimaryActorActorRefCSParserRuleCall_5_0() { return cPrimaryActorActorRefCSParserRuleCall_5_0; }

		//('secondaryActors' '=' '{' secondaryActors+=ActorRefCS (',' secondaryActors+=ActorRefCS)* '}')?
		public Group getGroup_6() { return cGroup_6; }

		//'secondaryActors'
		public Keyword getSecondaryActorsKeyword_6_0() { return cSecondaryActorsKeyword_6_0; }

		//'='
		public Keyword getEqualsSignKeyword_6_1() { return cEqualsSignKeyword_6_1; }

		//'{'
		public Keyword getLeftCurlyBracketKeyword_6_2() { return cLeftCurlyBracketKeyword_6_2; }

		//secondaryActors+=ActorRefCS
		public Assignment getSecondaryActorsAssignment_6_3() { return cSecondaryActorsAssignment_6_3; }

		//ActorRefCS
		public RuleCall getSecondaryActorsActorRefCSParserRuleCall_6_3_0() { return cSecondaryActorsActorRefCSParserRuleCall_6_3_0; }

		//(',' secondaryActors+=ActorRefCS)*
		public Group getGroup_6_4() { return cGroup_6_4; }

		//','
		public Keyword getCommaKeyword_6_4_0() { return cCommaKeyword_6_4_0; }

		//secondaryActors+=ActorRefCS
		public Assignment getSecondaryActorsAssignment_6_4_1() { return cSecondaryActorsAssignment_6_4_1; }

		//ActorRefCS
		public RuleCall getSecondaryActorsActorRefCSParserRuleCall_6_4_1_0() { return cSecondaryActorsActorRefCSParserRuleCall_6_4_1_0; }

		//'}'
		public Keyword getRightCurlyBracketKeyword_6_5() { return cRightCurlyBracketKeyword_6_5; }

		//precondition=UsecasePreconditionCS?
		public Assignment getPreconditionAssignment_7() { return cPreconditionAssignment_7; }

		//UsecasePreconditionCS
		public RuleCall getPreconditionUsecasePreconditionCSParserRuleCall_7_0() { return cPreconditionUsecasePreconditionCSParserRuleCall_7_0; }

		//postcondition=UsecasePostconditionCS?
		public Assignment getPostconditionAssignment_8() { return cPostconditionAssignment_8; }

		//UsecasePostconditionCS
		public RuleCall getPostconditionUsecasePostconditionCSParserRuleCall_8_0() { return cPostconditionUsecasePostconditionCSParserRuleCall_8_0; }

		//firstStep=StepCS
		public Assignment getFirstStepAssignment_9() { return cFirstStepAssignment_9; }

		//StepCS
		public RuleCall getFirstStepStepCSParserRuleCall_9_0() { return cFirstStepStepCSParserRuleCall_9_0; }

		//extPoints+=ExtensionPointCS*
		public Assignment getExtPointsAssignment_10() { return cExtPointsAssignment_10; }

		//ExtensionPointCS
		public RuleCall getExtPointsExtensionPointCSParserRuleCall_10_0() { return cExtPointsExtensionPointCSParserRuleCall_10_0; }

		//'end'
		public Keyword getEndKeyword_11() { return cEndKeyword_11; }
	}

	public class UsecasePreconditionCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.UsecasePreconditionCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cUcPreconditionKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cDescriptionKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cDescriptionAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_1_2_0 = (RuleCall)cDescriptionAssignment_1_2.eContents().get(0);
		private final Assignment cSnapshotAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cSnapshotSnapshotPatternCSParserRuleCall_2_0 = (RuleCall)cSnapshotAssignment_2.eContents().get(0);
		private final Keyword cEndKeyword_3 = (Keyword)cGroup.eContents().get(3);

		//UsecasePreconditionCS:
		//	'ucPrecondition' ('description' '=' description=StringLiteral)?
		//	snapshot=SnapshotPatternCS
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'ucPrecondition' ('description' '=' description=StringLiteral)?
		//snapshot=SnapshotPatternCS
		//'end'
		public Group getGroup() { return cGroup; }

		//'ucPrecondition'
		public Keyword getUcPreconditionKeyword_0() { return cUcPreconditionKeyword_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_1() { return cGroup_1; }

		//'description'
		public Keyword getDescriptionKeyword_1_0() { return cDescriptionKeyword_1_0; }

		//'='
		public Keyword getEqualsSignKeyword_1_1() { return cEqualsSignKeyword_1_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_1_2() { return cDescriptionAssignment_1_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_1_2_0() { return cDescriptionStringLiteralParserRuleCall_1_2_0; }

		//snapshot=SnapshotPatternCS
		public Assignment getSnapshotAssignment_2() { return cSnapshotAssignment_2; }

		//SnapshotPatternCS
		public RuleCall getSnapshotSnapshotPatternCSParserRuleCall_2_0() { return cSnapshotSnapshotPatternCSParserRuleCall_2_0; }

		//'end'
		public Keyword getEndKeyword_3() { return cEndKeyword_3; }
	}

	public class UsecasePostconditionCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.UsecasePostconditionCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cUcPostconditionKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cDescriptionKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cDescriptionAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_1_2_0 = (RuleCall)cDescriptionAssignment_1_2.eContents().get(0);
		private final Assignment cSnapshotAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cSnapshotSnapshotPatternCSParserRuleCall_2_0 = (RuleCall)cSnapshotAssignment_2.eContents().get(0);
		private final Keyword cEndKeyword_3 = (Keyword)cGroup.eContents().get(3);

		//UsecasePostconditionCS:
		//	'ucPostcondition' ('description' '=' description=StringLiteral)?
		//	snapshot=SnapshotPatternCS
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'ucPostcondition' ('description' '=' description=StringLiteral)?
		//snapshot=SnapshotPatternCS
		//'end'
		public Group getGroup() { return cGroup; }

		//'ucPostcondition'
		public Keyword getUcPostconditionKeyword_0() { return cUcPostconditionKeyword_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_1() { return cGroup_1; }

		//'description'
		public Keyword getDescriptionKeyword_1_0() { return cDescriptionKeyword_1_0; }

		//'='
		public Keyword getEqualsSignKeyword_1_1() { return cEqualsSignKeyword_1_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_1_2() { return cDescriptionAssignment_1_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_1_2_0() { return cDescriptionStringLiteralParserRuleCall_1_2_0; }

		//snapshot=SnapshotPatternCS
		public Assignment getSnapshotAssignment_2() { return cSnapshotAssignment_2; }

		//SnapshotPatternCS
		public RuleCall getSnapshotSnapshotPatternCSParserRuleCall_2_0() { return cSnapshotSnapshotPatternCSParserRuleCall_2_0; }

		//'end'
		public Keyword getEndKeyword_3() { return cEndKeyword_3; }
	}

	public class StepCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.StepCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Alternatives cAlternatives_0 = (Alternatives)cGroup.eContents().get(0);
		private final RuleCall cActStepCSParserRuleCall_0_0 = (RuleCall)cAlternatives_0.eContents().get(0);
		private final RuleCall cRejoinStepCSParserRuleCall_0_1 = (RuleCall)cAlternatives_0.eContents().get(1);
		private final RuleCall cUCStepCSParserRuleCall_0_2 = (RuleCall)cAlternatives_0.eContents().get(2);
		private final Assignment cAltFlowsAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cAltFlowsAltFlowCSParserRuleCall_1_0 = (RuleCall)cAltFlowsAssignment_1.eContents().get(0);
		private final Assignment cNextStepAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNextStepStepCSParserRuleCall_2_0 = (RuleCall)cNextStepAssignment_2.eContents().get(0);

		//StepCS:
		//	(ActStepCS | RejoinStepCS | UCStepCS) altFlows+=AltFlowCS*
		//	nextStep=StepCS?;
		@Override public ParserRule getRule() { return rule; }

		//(ActStepCS | RejoinStepCS | UCStepCS) altFlows+=AltFlowCS*
		//nextStep=StepCS?
		public Group getGroup() { return cGroup; }

		//(ActStepCS | RejoinStepCS | UCStepCS)
		public Alternatives getAlternatives_0() { return cAlternatives_0; }

		//ActStepCS
		public RuleCall getActStepCSParserRuleCall_0_0() { return cActStepCSParserRuleCall_0_0; }

		//RejoinStepCS
		public RuleCall getRejoinStepCSParserRuleCall_0_1() { return cRejoinStepCSParserRuleCall_0_1; }

		//UCStepCS
		public RuleCall getUCStepCSParserRuleCall_0_2() { return cUCStepCSParserRuleCall_0_2; }

		//altFlows+=AltFlowCS*
		public Assignment getAltFlowsAssignment_1() { return cAltFlowsAssignment_1; }

		//AltFlowCS
		public RuleCall getAltFlowsAltFlowCSParserRuleCall_1_0() { return cAltFlowsAltFlowCSParserRuleCall_1_0; }

		//nextStep=StepCS?
		public Assignment getNextStepAssignment_2() { return cNextStepAssignment_2; }

		//StepCS
		public RuleCall getNextStepStepCSParserRuleCall_2_0() { return cNextStepStepCSParserRuleCall_2_0; }
	}

	public class ActStepCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ActStepCS");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cActorStepCSParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cSystemStepCSParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);

		//ActStepCS:
		//	ActorStepCS | SystemStepCS;
		@Override public ParserRule getRule() { return rule; }

		//ActorStepCS | SystemStepCS
		public Alternatives getAlternatives() { return cAlternatives; }

		//ActorStepCS
		public RuleCall getActorStepCSParserRuleCall_0() { return cActorStepCSParserRuleCall_0; }

		//SystemStepCS
		public RuleCall getSystemStepCSParserRuleCall_1() { return cSystemStepCSParserRuleCall_1; }
	}

	public class ActorStepCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ActorStepCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cIsActorStepAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final Keyword cIsActorStepActStepKeyword_0_0 = (Keyword)cIsActorStepAssignment_0.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDescriptionKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDescriptionAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_2_2_0 = (RuleCall)cDescriptionAssignment_2_2.eContents().get(0);
		private final Keyword cFromKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cPreSnapshotAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cPreSnapshotSnapshotPatternCSParserRuleCall_4_0 = (RuleCall)cPreSnapshotAssignment_4.eContents().get(0);
		private final Keyword cToKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cPostSnapshotAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cPostSnapshotSnapshotPatternCSParserRuleCall_6_0 = (RuleCall)cPostSnapshotAssignment_6.eContents().get(0);
		private final Group cGroup_7 = (Group)cGroup.eContents().get(7);
		private final Keyword cActionsKeyword_7_0 = (Keyword)cGroup_7.eContents().get(0);
		private final Assignment cActionsAssignment_7_1 = (Assignment)cGroup_7.eContents().get(1);
		private final RuleCall cActionsActorActionCSParserRuleCall_7_1_0 = (RuleCall)cActionsAssignment_7_1.eContents().get(0);
		private final Keyword cEndKeyword_8 = (Keyword)cGroup.eContents().get(8);

		//ActorStepCS ActStepCS:
		//	isActorStep?='actStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
		//	'from'
		//	preSnapshot=SnapshotPatternCS
		//	'to'
		//	postSnapshot=SnapshotPatternCS ('actions' actions+=ActorActionCS*)?
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//isActorStep?='actStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
		//'from'
		//preSnapshot=SnapshotPatternCS
		//'to'
		//postSnapshot=SnapshotPatternCS ('actions' actions+=ActorActionCS*)?
		//'end'
		public Group getGroup() { return cGroup; }

		//isActorStep?='actStep'
		public Assignment getIsActorStepAssignment_0() { return cIsActorStepAssignment_0; }

		//'actStep'
		public Keyword getIsActorStepActStepKeyword_0_0() { return cIsActorStepActStepKeyword_0_0; }

		//name=UnrestrictedName?
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_2() { return cGroup_2; }

		//'description'
		public Keyword getDescriptionKeyword_2_0() { return cDescriptionKeyword_2_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_2_2() { return cDescriptionAssignment_2_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_2_2_0() { return cDescriptionStringLiteralParserRuleCall_2_2_0; }

		//'from'
		public Keyword getFromKeyword_3() { return cFromKeyword_3; }

		//preSnapshot=SnapshotPatternCS
		public Assignment getPreSnapshotAssignment_4() { return cPreSnapshotAssignment_4; }

		//SnapshotPatternCS
		public RuleCall getPreSnapshotSnapshotPatternCSParserRuleCall_4_0() { return cPreSnapshotSnapshotPatternCSParserRuleCall_4_0; }

		//'to'
		public Keyword getToKeyword_5() { return cToKeyword_5; }

		//postSnapshot=SnapshotPatternCS
		public Assignment getPostSnapshotAssignment_6() { return cPostSnapshotAssignment_6; }

		//SnapshotPatternCS
		public RuleCall getPostSnapshotSnapshotPatternCSParserRuleCall_6_0() { return cPostSnapshotSnapshotPatternCSParserRuleCall_6_0; }

		//('actions' actions+=ActorActionCS*)?
		public Group getGroup_7() { return cGroup_7; }

		//'actions'
		public Keyword getActionsKeyword_7_0() { return cActionsKeyword_7_0; }

		//actions+=ActorActionCS*
		public Assignment getActionsAssignment_7_1() { return cActionsAssignment_7_1; }

		//ActorActionCS
		public RuleCall getActionsActorActionCSParserRuleCall_7_1_0() { return cActionsActorActionCSParserRuleCall_7_1_0; }

		//'end'
		public Keyword getEndKeyword_8() { return cEndKeyword_8; }
	}

	public class SystemStepCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.SystemStepCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cSysStepKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDescriptionKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDescriptionAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_2_2_0 = (RuleCall)cDescriptionAssignment_2_2.eContents().get(0);
		private final Keyword cFromKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cPreSnapshotAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cPreSnapshotSnapshotPatternCSParserRuleCall_4_0 = (RuleCall)cPreSnapshotAssignment_4.eContents().get(0);
		private final Keyword cToKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cPostSnapshotAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cPostSnapshotSnapshotPatternCSParserRuleCall_6_0 = (RuleCall)cPostSnapshotAssignment_6.eContents().get(0);
		private final Group cGroup_7 = (Group)cGroup.eContents().get(7);
		private final Keyword cActionsKeyword_7_0 = (Keyword)cGroup_7.eContents().get(0);
		private final Assignment cActionsAssignment_7_1 = (Assignment)cGroup_7.eContents().get(1);
		private final RuleCall cActionsSystemActionCSParserRuleCall_7_1_0 = (RuleCall)cActionsAssignment_7_1.eContents().get(0);
		private final Keyword cEndKeyword_8 = (Keyword)cGroup.eContents().get(8);

		//SystemStepCS ActStepCS:
		//	'sysStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
		//	'from'
		//	preSnapshot=SnapshotPatternCS
		//	'to'
		//	postSnapshot=SnapshotPatternCS ('actions' actions+=SystemActionCS*)?
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'sysStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
		//'from'
		//preSnapshot=SnapshotPatternCS
		//'to'
		//postSnapshot=SnapshotPatternCS ('actions' actions+=SystemActionCS*)?
		//'end'
		public Group getGroup() { return cGroup; }

		//'sysStep'
		public Keyword getSysStepKeyword_0() { return cSysStepKeyword_0; }

		//name=UnrestrictedName?
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_2() { return cGroup_2; }

		//'description'
		public Keyword getDescriptionKeyword_2_0() { return cDescriptionKeyword_2_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_2_2() { return cDescriptionAssignment_2_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_2_2_0() { return cDescriptionStringLiteralParserRuleCall_2_2_0; }

		//'from'
		public Keyword getFromKeyword_3() { return cFromKeyword_3; }

		//preSnapshot=SnapshotPatternCS
		public Assignment getPreSnapshotAssignment_4() { return cPreSnapshotAssignment_4; }

		//SnapshotPatternCS
		public RuleCall getPreSnapshotSnapshotPatternCSParserRuleCall_4_0() { return cPreSnapshotSnapshotPatternCSParserRuleCall_4_0; }

		//'to'
		public Keyword getToKeyword_5() { return cToKeyword_5; }

		//postSnapshot=SnapshotPatternCS
		public Assignment getPostSnapshotAssignment_6() { return cPostSnapshotAssignment_6; }

		//SnapshotPatternCS
		public RuleCall getPostSnapshotSnapshotPatternCSParserRuleCall_6_0() { return cPostSnapshotSnapshotPatternCSParserRuleCall_6_0; }

		//('actions' actions+=SystemActionCS*)?
		public Group getGroup_7() { return cGroup_7; }

		//'actions'
		public Keyword getActionsKeyword_7_0() { return cActionsKeyword_7_0; }

		//actions+=SystemActionCS*
		public Assignment getActionsAssignment_7_1() { return cActionsAssignment_7_1; }

		//SystemActionCS
		public RuleCall getActionsSystemActionCSParserRuleCall_7_1_0() { return cActionsSystemActionCSParserRuleCall_7_1_0; }

		//'end'
		public Keyword getEndKeyword_8() { return cEndKeyword_8; }
	}

	public class ActorActionCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ActorActionCS");
		private final RuleCall cInputActionCSParserRuleCall = (RuleCall)rule.eContents().get(1);

		//ActorActionCS:
		//	InputActionCS;
		@Override public ParserRule getRule() { return rule; }

		//InputActionCS
		public RuleCall getInputActionCSParserRuleCall() { return cInputActionCSParserRuleCall; }
	}

	public class SystemActionCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.SystemActionCS");
		private final RuleCall cOutputActionCSParserRuleCall = (RuleCall)rule.eContents().get(1);

		//SystemActionCS:
		//	OutputActionCS;
		@Override public ParserRule getRule() { return rule; }

		//OutputActionCS
		public RuleCall getOutputActionCSParserRuleCall() { return cOutputActionCSParserRuleCall; }
	}

	public class InputActionCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.InputActionCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cActorAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cActorActorRefCSParserRuleCall_0_0 = (RuleCall)cActorAssignment_0.eContents().get(0);
		private final Keyword cHyphenMinusGreaterThanSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Assignment cObjVarsAssignment_2_0 = (Assignment)cGroup_2.eContents().get(0);
		private final RuleCall cObjVarsObjVarCSParserRuleCall_2_0_0 = (RuleCall)cObjVarsAssignment_2_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);

		//InputActionCS ActorActionCS:
		//	actor=ActorRefCS? '->' (objVars+=ObjVarCS ';')+;
		@Override public ParserRule getRule() { return rule; }

		//actor=ActorRefCS? '->' (objVars+=ObjVarCS ';')+
		public Group getGroup() { return cGroup; }

		//actor=ActorRefCS?
		public Assignment getActorAssignment_0() { return cActorAssignment_0; }

		//ActorRefCS
		public RuleCall getActorActorRefCSParserRuleCall_0_0() { return cActorActorRefCSParserRuleCall_0_0; }

		//'->'
		public Keyword getHyphenMinusGreaterThanSignKeyword_1() { return cHyphenMinusGreaterThanSignKeyword_1; }

		//(objVars+=ObjVarCS ';')+
		public Group getGroup_2() { return cGroup_2; }

		//objVars+=ObjVarCS
		public Assignment getObjVarsAssignment_2_0() { return cObjVarsAssignment_2_0; }

		//ObjVarCS
		public RuleCall getObjVarsObjVarCSParserRuleCall_2_0_0() { return cObjVarsObjVarCSParserRuleCall_2_0_0; }

		//';'
		public Keyword getSemicolonKeyword_2_1() { return cSemicolonKeyword_2_1; }
	}

	public class OutputActionCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.OutputActionCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cActorAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cActorActorRefCSParserRuleCall_0_0 = (RuleCall)cActorAssignment_0.eContents().get(0);
		private final Keyword cLessThanSignHyphenMinusKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Assignment cObjVarsAssignment_2_0 = (Assignment)cGroup_2.eContents().get(0);
		private final RuleCall cObjVarsObjVarCSParserRuleCall_2_0_0 = (RuleCall)cObjVarsAssignment_2_0.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cValuesAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cValuesSpecificationCSParserRuleCall_2_2_0 = (RuleCall)cValuesAssignment_2_2.eContents().get(0);
		private final Keyword cSemicolonKeyword_2_3 = (Keyword)cGroup_2.eContents().get(3);

		//OutputActionCS SystemActionCS:
		//	actor=ActorRefCS '<-' (objVars+=ObjVarCS '=' values+=SpecificationCS ';')+;
		@Override public ParserRule getRule() { return rule; }

		//actor=ActorRefCS '<-' (objVars+=ObjVarCS '=' values+=SpecificationCS ';')+
		public Group getGroup() { return cGroup; }

		//actor=ActorRefCS
		public Assignment getActorAssignment_0() { return cActorAssignment_0; }

		//ActorRefCS
		public RuleCall getActorActorRefCSParserRuleCall_0_0() { return cActorActorRefCSParserRuleCall_0_0; }

		//'<-'
		public Keyword getLessThanSignHyphenMinusKeyword_1() { return cLessThanSignHyphenMinusKeyword_1; }

		//(objVars+=ObjVarCS '=' values+=SpecificationCS ';')+
		public Group getGroup_2() { return cGroup_2; }

		//objVars+=ObjVarCS
		public Assignment getObjVarsAssignment_2_0() { return cObjVarsAssignment_2_0; }

		//ObjVarCS
		public RuleCall getObjVarsObjVarCSParserRuleCall_2_0_0() { return cObjVarsObjVarCSParserRuleCall_2_0_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//values+=SpecificationCS
		public Assignment getValuesAssignment_2_2() { return cValuesAssignment_2_2; }

		//SpecificationCS
		public RuleCall getValuesSpecificationCSParserRuleCall_2_2_0() { return cValuesSpecificationCSParserRuleCall_2_2_0; }

		//';'
		public Keyword getSemicolonKeyword_2_3() { return cSemicolonKeyword_2_3; }
	}

	public class RejoinStepCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.RejoinStepCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cRejoinStepKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cRejoinToAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cRejoinToStepRefCSParserRuleCall_1_0 = (RuleCall)cRejoinToAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDescriptionKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDescriptionAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_2_2_0 = (RuleCall)cDescriptionAssignment_2_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cWhenKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cConditionAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cConditionSnapshotPatternCSParserRuleCall_3_1_0 = (RuleCall)cConditionAssignment_3_1.eContents().get(0);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);

		//RejoinStepCS:
		//	'rejoinStep' rejoinTo=StepRefCS ('description' '=' description=StringLiteral)? ('when'
		//	condition=SnapshotPatternCS)?
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'rejoinStep' rejoinTo=StepRefCS ('description' '=' description=StringLiteral)? ('when'
		//condition=SnapshotPatternCS)?
		//'end'
		public Group getGroup() { return cGroup; }

		//'rejoinStep'
		public Keyword getRejoinStepKeyword_0() { return cRejoinStepKeyword_0; }

		//rejoinTo=StepRefCS
		public Assignment getRejoinToAssignment_1() { return cRejoinToAssignment_1; }

		//StepRefCS
		public RuleCall getRejoinToStepRefCSParserRuleCall_1_0() { return cRejoinToStepRefCSParserRuleCall_1_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_2() { return cGroup_2; }

		//'description'
		public Keyword getDescriptionKeyword_2_0() { return cDescriptionKeyword_2_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_2_2() { return cDescriptionAssignment_2_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_2_2_0() { return cDescriptionStringLiteralParserRuleCall_2_2_0; }

		//('when'
		//condition=SnapshotPatternCS)?
		public Group getGroup_3() { return cGroup_3; }

		//'when'
		public Keyword getWhenKeyword_3_0() { return cWhenKeyword_3_0; }

		//condition=SnapshotPatternCS
		public Assignment getConditionAssignment_3_1() { return cConditionAssignment_3_1; }

		//SnapshotPatternCS
		public RuleCall getConditionSnapshotPatternCSParserRuleCall_3_1_0() { return cConditionSnapshotPatternCSParserRuleCall_3_1_0; }

		//'end'
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }
	}

	public class UCStepCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.UCStepCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cInclStepKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDescriptionKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDescriptionAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_2_2_0 = (RuleCall)cDescriptionAssignment_2_2.eContents().get(0);
		private final Assignment cIncludedUCAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cIncludedUCUsecaseRefCSParserRuleCall_3_0 = (RuleCall)cIncludedUCAssignment_3.eContents().get(0);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);

		//UCStepCS:
		//	'inclStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
		//	includedUC=UsecaseRefCS
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'inclStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
		//includedUC=UsecaseRefCS
		//'end'
		public Group getGroup() { return cGroup; }

		//'inclStep'
		public Keyword getInclStepKeyword_0() { return cInclStepKeyword_0; }

		//name=UnrestrictedName?
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_2() { return cGroup_2; }

		//'description'
		public Keyword getDescriptionKeyword_2_0() { return cDescriptionKeyword_2_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_2_2() { return cDescriptionAssignment_2_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_2_2_0() { return cDescriptionStringLiteralParserRuleCall_2_2_0; }

		//includedUC=UsecaseRefCS
		public Assignment getIncludedUCAssignment_3() { return cIncludedUCAssignment_3; }

		//UsecaseRefCS
		public RuleCall getIncludedUCUsecaseRefCSParserRuleCall_3_0() { return cIncludedUCUsecaseRefCSParserRuleCall_3_0; }

		//'end'
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }
	}

	public class AltFlowCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.AltFlowCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cAltStepKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cAtKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cBaseStepAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cBaseStepStepRefCSParserRuleCall_3_0 = (RuleCall)cBaseStepAssignment_3.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cDescriptionKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Keyword cEqualsSignKeyword_4_1 = (Keyword)cGroup_4.eContents().get(1);
		private final Assignment cDescriptionAssignment_4_2 = (Assignment)cGroup_4.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_4_2_0 = (RuleCall)cDescriptionAssignment_4_2.eContents().get(0);
		private final Keyword cWhenKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cConditionAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cConditionSnapshotPatternCSParserRuleCall_6_0 = (RuleCall)cConditionAssignment_6.eContents().get(0);
		private final Keyword cEndKeyword_7 = (Keyword)cGroup.eContents().get(7);
		private final Assignment cAltStepAssignment_8 = (Assignment)cGroup.eContents().get(8);
		private final RuleCall cAltStepStepCSParserRuleCall_8_0 = (RuleCall)cAltStepAssignment_8.eContents().get(0);
		private final Keyword cEndKeyword_9 = (Keyword)cGroup.eContents().get(9);

		//AltFlowCS:
		//	'altStep' name=UnrestrictedName? 'at' baseStep=StepRefCS ('description' '=' description=StringLiteral)?
		//	'when'
		//	condition=SnapshotPatternCS
		//	'end'
		//	altStep=StepCS
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'altStep' name=UnrestrictedName? 'at' baseStep=StepRefCS ('description' '=' description=StringLiteral)?
		//'when'
		//condition=SnapshotPatternCS
		//'end'
		//altStep=StepCS
		//'end'
		public Group getGroup() { return cGroup; }

		//'altStep'
		public Keyword getAltStepKeyword_0() { return cAltStepKeyword_0; }

		//name=UnrestrictedName?
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//'at'
		public Keyword getAtKeyword_2() { return cAtKeyword_2; }

		//baseStep=StepRefCS
		public Assignment getBaseStepAssignment_3() { return cBaseStepAssignment_3; }

		//StepRefCS
		public RuleCall getBaseStepStepRefCSParserRuleCall_3_0() { return cBaseStepStepRefCSParserRuleCall_3_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_4() { return cGroup_4; }

		//'description'
		public Keyword getDescriptionKeyword_4_0() { return cDescriptionKeyword_4_0; }

		//'='
		public Keyword getEqualsSignKeyword_4_1() { return cEqualsSignKeyword_4_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_4_2() { return cDescriptionAssignment_4_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_4_2_0() { return cDescriptionStringLiteralParserRuleCall_4_2_0; }

		//'when'
		public Keyword getWhenKeyword_5() { return cWhenKeyword_5; }

		//condition=SnapshotPatternCS
		public Assignment getConditionAssignment_6() { return cConditionAssignment_6; }

		//SnapshotPatternCS
		public RuleCall getConditionSnapshotPatternCSParserRuleCall_6_0() { return cConditionSnapshotPatternCSParserRuleCall_6_0; }

		//'end'
		public Keyword getEndKeyword_7() { return cEndKeyword_7; }

		//altStep=StepCS
		public Assignment getAltStepAssignment_8() { return cAltStepAssignment_8; }

		//StepCS
		public RuleCall getAltStepStepCSParserRuleCall_8_0() { return cAltStepStepCSParserRuleCall_8_0; }

		//'end'
		public Keyword getEndKeyword_9() { return cEndKeyword_9; }
	}

	public class SnapshotPatternCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.SnapshotPatternCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cSnapshotPatternCSAction_0 = (Action)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cSnapshotIDKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Keyword cEqualsSignKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cNameAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_2_0 = (RuleCall)cNameAssignment_1_2.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDescriptionKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDescriptionAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_2_2_0 = (RuleCall)cDescriptionAssignment_2_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Alternatives cAlternatives_3_0 = (Alternatives)cGroup_3.eContents().get(0);
		private final Assignment cObjectsAssignment_3_0_0 = (Assignment)cAlternatives_3_0.eContents().get(0);
		private final RuleCall cObjectsObjVarCSParserRuleCall_3_0_0_0 = (RuleCall)cObjectsAssignment_3_0_0.eContents().get(0);
		private final Assignment cNegObjectsAssignment_3_0_1 = (Assignment)cAlternatives_3_0.eContents().get(1);
		private final RuleCall cNegObjectsNegObjVarCSParserRuleCall_3_0_1_0 = (RuleCall)cNegObjectsAssignment_3_0_1.eContents().get(0);
		private final Keyword cSemicolonKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Assignment cLinksAssignment_4_0 = (Assignment)cGroup_4.eContents().get(0);
		private final RuleCall cLinksVarLinkCSParserRuleCall_4_0_0 = (RuleCall)cLinksAssignment_4_0.eContents().get(0);
		private final Keyword cSemicolonKeyword_4_1 = (Keyword)cGroup_4.eContents().get(1);
		private final Assignment cConstraintsAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cConstraintsConstraintCSParserRuleCall_5_0 = (RuleCall)cConstraintsAssignment_5.eContents().get(0);

		//SnapshotPatternCS:
		//	{SnapshotPatternCS} ('snapshotID' '=' name=UnrestrictedName)? ('description' '=' description=StringLiteral)? (
		//	(objects+=ObjVarCS | negObjects+=NegObjVarCS) ';')* (links+=VarLinkCS ';')*
		//	constraints+=ConstraintCS*;
		@Override public ParserRule getRule() { return rule; }

		//{SnapshotPatternCS} ('snapshotID' '=' name=UnrestrictedName)? ('description' '=' description=StringLiteral)? (
		//(objects+=ObjVarCS | negObjects+=NegObjVarCS) ';')* (links+=VarLinkCS ';')*
		//constraints+=ConstraintCS*
		public Group getGroup() { return cGroup; }

		//{SnapshotPatternCS}
		public Action getSnapshotPatternCSAction_0() { return cSnapshotPatternCSAction_0; }

		//('snapshotID' '=' name=UnrestrictedName)?
		public Group getGroup_1() { return cGroup_1; }

		//'snapshotID'
		public Keyword getSnapshotIDKeyword_1_0() { return cSnapshotIDKeyword_1_0; }

		//'='
		public Keyword getEqualsSignKeyword_1_1() { return cEqualsSignKeyword_1_1; }

		//name=UnrestrictedName
		public Assignment getNameAssignment_1_2() { return cNameAssignment_1_2; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_2_0() { return cNameUnrestrictedNameParserRuleCall_1_2_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_2() { return cGroup_2; }

		//'description'
		public Keyword getDescriptionKeyword_2_0() { return cDescriptionKeyword_2_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_2_2() { return cDescriptionAssignment_2_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_2_2_0() { return cDescriptionStringLiteralParserRuleCall_2_2_0; }

		//((objects+=ObjVarCS | negObjects+=NegObjVarCS) ';')*
		public Group getGroup_3() { return cGroup_3; }

		//(objects+=ObjVarCS | negObjects+=NegObjVarCS)
		public Alternatives getAlternatives_3_0() { return cAlternatives_3_0; }

		//objects+=ObjVarCS
		public Assignment getObjectsAssignment_3_0_0() { return cObjectsAssignment_3_0_0; }

		//ObjVarCS
		public RuleCall getObjectsObjVarCSParserRuleCall_3_0_0_0() { return cObjectsObjVarCSParserRuleCall_3_0_0_0; }

		//negObjects+=NegObjVarCS
		public Assignment getNegObjectsAssignment_3_0_1() { return cNegObjectsAssignment_3_0_1; }

		//NegObjVarCS
		public RuleCall getNegObjectsNegObjVarCSParserRuleCall_3_0_1_0() { return cNegObjectsNegObjVarCSParserRuleCall_3_0_1_0; }

		//';'
		public Keyword getSemicolonKeyword_3_1() { return cSemicolonKeyword_3_1; }

		//(links+=VarLinkCS ';')*
		public Group getGroup_4() { return cGroup_4; }

		//links+=VarLinkCS
		public Assignment getLinksAssignment_4_0() { return cLinksAssignment_4_0; }

		//VarLinkCS
		public RuleCall getLinksVarLinkCSParserRuleCall_4_0_0() { return cLinksVarLinkCSParserRuleCall_4_0_0; }

		//';'
		public Keyword getSemicolonKeyword_4_1() { return cSemicolonKeyword_4_1; }

		//constraints+=ConstraintCS*
		public Assignment getConstraintsAssignment_5() { return cConstraintsAssignment_5; }

		//ConstraintCS
		public RuleCall getConstraintsConstraintCSParserRuleCall_5_0() { return cConstraintsConstraintCSParserRuleCall_5_0; }
	}

	public class ObjVarCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ObjVarCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cIsMatchedAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final Keyword cIsMatchedDollarSignKeyword_0_0 = (Keyword)cIsMatchedAssignment_0.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cColonKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cTypeAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cTypeTypedRefCSParserRuleCall_3_0 = (RuleCall)cTypeAssignment_3.eContents().get(0);

		//ObjVarCS:
		//	isMatched?='$'? name=UnrestrictedName ':' type=TypedRefCS;
		@Override public ParserRule getRule() { return rule; }

		//isMatched?='$'? name=UnrestrictedName ':' type=TypedRefCS
		public Group getGroup() { return cGroup; }

		//isMatched?='$'?
		public Assignment getIsMatchedAssignment_0() { return cIsMatchedAssignment_0; }

		//'$'
		public Keyword getIsMatchedDollarSignKeyword_0_0() { return cIsMatchedDollarSignKeyword_0_0; }

		//name=UnrestrictedName
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//':'
		public Keyword getColonKeyword_2() { return cColonKeyword_2; }

		//type=TypedRefCS
		public Assignment getTypeAssignment_3() { return cTypeAssignment_3; }

		//TypedRefCS
		public RuleCall getTypeTypedRefCSParserRuleCall_3_0() { return cTypeTypedRefCSParserRuleCall_3_0; }
	}

	public class NegObjVarCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.NegObjVarCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cExclamationMarkKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cObjVarRefCSParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);

		//NegObjVarCS base::ModelElementRefCS:
		//	'!' ObjVarRefCS;
		@Override public ParserRule getRule() { return rule; }

		//'!' ObjVarRefCS
		public Group getGroup() { return cGroup; }

		//'!'
		public Keyword getExclamationMarkKeyword_0() { return cExclamationMarkKeyword_0; }

		//ObjVarRefCS
		public RuleCall getObjVarRefCSParserRuleCall_1() { return cObjVarRefCSParserRuleCall_1; }
	}

	public class VarLinkCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.VarLinkCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cIsNegAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final Keyword cIsNegExclamationMarkKeyword_0_0 = (Keyword)cIsNegAssignment_0.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cObjVarsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cObjVarsObjVarRefCSParserRuleCall_2_0 = (RuleCall)cObjVarsAssignment_2.eContents().get(0);
		private final Keyword cCommaKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cObjVarsAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cObjVarsObjVarRefCSParserRuleCall_4_0 = (RuleCall)cObjVarsAssignment_4.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Keyword cColonKeyword_6 = (Keyword)cGroup.eContents().get(6);
		private final Assignment cAssocAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cAssocAssocRefCSParserRuleCall_7_0 = (RuleCall)cAssocAssignment_7.eContents().get(0);

		//VarLinkCS:
		//	isNeg?='!'? '(' objVars+=ObjVarRefCS ',' objVars+=ObjVarRefCS ')' ':' assoc=AssocRefCS;
		@Override public ParserRule getRule() { return rule; }

		//isNeg?='!'? '(' objVars+=ObjVarRefCS ',' objVars+=ObjVarRefCS ')' ':' assoc=AssocRefCS
		public Group getGroup() { return cGroup; }

		//isNeg?='!'?
		public Assignment getIsNegAssignment_0() { return cIsNegAssignment_0; }

		//'!'
		public Keyword getIsNegExclamationMarkKeyword_0_0() { return cIsNegExclamationMarkKeyword_0_0; }

		//'('
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }

		//objVars+=ObjVarRefCS
		public Assignment getObjVarsAssignment_2() { return cObjVarsAssignment_2; }

		//ObjVarRefCS
		public RuleCall getObjVarsObjVarRefCSParserRuleCall_2_0() { return cObjVarsObjVarRefCSParserRuleCall_2_0; }

		//','
		public Keyword getCommaKeyword_3() { return cCommaKeyword_3; }

		//objVars+=ObjVarRefCS
		public Assignment getObjVarsAssignment_4() { return cObjVarsAssignment_4; }

		//ObjVarRefCS
		public RuleCall getObjVarsObjVarRefCSParserRuleCall_4_0() { return cObjVarsObjVarRefCSParserRuleCall_4_0; }

		//')'
		public Keyword getRightParenthesisKeyword_5() { return cRightParenthesisKeyword_5; }

		//':'
		public Keyword getColonKeyword_6() { return cColonKeyword_6; }

		//assoc=AssocRefCS
		public Assignment getAssocAssignment_7() { return cAssocAssignment_7; }

		//AssocRefCS
		public RuleCall getAssocAssocRefCSParserRuleCall_7_0() { return cAssocAssocRefCSParserRuleCall_7_0; }
	}

	public class AssociationCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.AssociationCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cAssociationKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cAssocEndsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cAssocEndsAssocEndCSParserRuleCall_2_0 = (RuleCall)cAssocEndsAssignment_2.eContents().get(0);
		private final Assignment cAssocEndsAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cAssocEndsAssocEndCSParserRuleCall_3_0 = (RuleCall)cAssocEndsAssignment_3.eContents().get(0);
		private final Keyword cEndKeyword_4 = (Keyword)cGroup.eContents().get(4);

		//AssociationCS:
		//	'association' name=UnrestrictedName
		//	assocEnds+=AssocEndCS
		//	assocEnds+=AssocEndCS
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'association' name=UnrestrictedName
		//assocEnds+=AssocEndCS
		//assocEnds+=AssocEndCS
		//'end'
		public Group getGroup() { return cGroup; }

		//'association'
		public Keyword getAssociationKeyword_0() { return cAssociationKeyword_0; }

		//name=UnrestrictedName
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//assocEnds+=AssocEndCS
		public Assignment getAssocEndsAssignment_2() { return cAssocEndsAssignment_2; }

		//AssocEndCS
		public RuleCall getAssocEndsAssocEndCSParserRuleCall_2_0() { return cAssocEndsAssocEndCSParserRuleCall_2_0; }

		//assocEnds+=AssocEndCS
		public Assignment getAssocEndsAssignment_3() { return cAssocEndsAssignment_3; }

		//AssocEndCS
		public RuleCall getAssocEndsAssocEndCSParserRuleCall_3_0() { return cAssocEndsAssocEndCSParserRuleCall_3_0; }

		//'end'
		public Keyword getEndKeyword_4() { return cEndKeyword_4; }
	}

	public class ConstraintCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ConstraintCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cLeftSquareBracketKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cConstrExprAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cConstrExprSpecificationCSParserRuleCall_1_0 = (RuleCall)cConstrExprAssignment_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);

		//ConstraintCS:
		//	'[' constrExpr=SpecificationCS ']';
		@Override public ParserRule getRule() { return rule; }

		//'[' constrExpr=SpecificationCS ']'
		public Group getGroup() { return cGroup; }

		//'['
		public Keyword getLeftSquareBracketKeyword_0() { return cLeftSquareBracketKeyword_0; }

		//constrExpr=SpecificationCS
		public Assignment getConstrExprAssignment_1() { return cConstrExprAssignment_1; }

		//SpecificationCS
		public RuleCall getConstrExprSpecificationCSParserRuleCall_1_0() { return cConstrExprSpecificationCSParserRuleCall_1_0; }

		//']'
		public Keyword getRightSquareBracketKeyword_2() { return cRightSquareBracketKeyword_2; }
	}

	public class ActorCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ActorCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cActorKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cDescriptionKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2_1 = (Keyword)cGroup_2.eContents().get(1);
		private final Assignment cDescriptionAssignment_2_2 = (Assignment)cGroup_2.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_2_2_0 = (RuleCall)cDescriptionAssignment_2_2.eContents().get(0);
		private final Keyword cEndKeyword_3 = (Keyword)cGroup.eContents().get(3);

		//ActorCS:
		//	'actor' name=UnrestrictedName ('description' '=' description=StringLiteral)?
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//'actor' name=UnrestrictedName ('description' '=' description=StringLiteral)?
		//'end'
		public Group getGroup() { return cGroup; }

		//'actor'
		public Keyword getActorKeyword_0() { return cActorKeyword_0; }

		//name=UnrestrictedName
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_1_0() { return cNameUnrestrictedNameParserRuleCall_1_0; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_2() { return cGroup_2; }

		//'description'
		public Keyword getDescriptionKeyword_2_0() { return cDescriptionKeyword_2_0; }

		//'='
		public Keyword getEqualsSignKeyword_2_1() { return cEqualsSignKeyword_2_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_2_2() { return cDescriptionAssignment_2_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_2_2_0() { return cDescriptionStringLiteralParserRuleCall_2_2_0; }

		//'end'
		public Keyword getEndKeyword_3() { return cEndKeyword_3; }
	}

	public class ExtensionPointCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ExtensionPointCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExtensionPointCSAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cExtensionPointKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cNameAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_2_0 = (RuleCall)cNameAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cAtKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_3_1 = (Keyword)cGroup_3.eContents().get(1);
		private final Assignment cLocationsAssignment_3_2 = (Assignment)cGroup_3.eContents().get(2);
		private final RuleCall cLocationsStepRefCSParserRuleCall_3_2_0 = (RuleCall)cLocationsAssignment_3_2.eContents().get(0);
		private final Group cGroup_3_3 = (Group)cGroup_3.eContents().get(3);
		private final Keyword cVerticalLineKeyword_3_3_0 = (Keyword)cGroup_3_3.eContents().get(0);
		private final Assignment cLocationsAssignment_3_3_1 = (Assignment)cGroup_3_3.eContents().get(1);
		private final RuleCall cLocationsStepRefCSParserRuleCall_3_3_1_0 = (RuleCall)cLocationsAssignment_3_3_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_3_4 = (Keyword)cGroup_3.eContents().get(4);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cDescriptionKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Keyword cEqualsSignKeyword_4_1 = (Keyword)cGroup_4.eContents().get(1);
		private final Assignment cDescriptionAssignment_4_2 = (Assignment)cGroup_4.eContents().get(2);
		private final RuleCall cDescriptionStringLiteralParserRuleCall_4_2_0 = (RuleCall)cDescriptionAssignment_4_2.eContents().get(0);
		private final Keyword cWhenKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cConditionAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cConditionSnapshotPatternCSParserRuleCall_6_0 = (RuleCall)cConditionAssignment_6.eContents().get(0);
		private final Keyword cEndKeyword_7 = (Keyword)cGroup.eContents().get(7);

		//ExtensionPointCS:
		//	{ExtensionPointCS} 'extensionPoint' name=UnrestrictedName? ('at' '{' locations+=StepRefCS ('|' locations+=StepRefCS)
		//	* '}')? ('description' '=' description=StringLiteral)?
		//	'when'
		//	condition=SnapshotPatternCS?
		//	'end';
		@Override public ParserRule getRule() { return rule; }

		//{ExtensionPointCS} 'extensionPoint' name=UnrestrictedName? ('at' '{' locations+=StepRefCS ('|' locations+=StepRefCS)*
		//'}')? ('description' '=' description=StringLiteral)?
		//'when'
		//condition=SnapshotPatternCS?
		//'end'
		public Group getGroup() { return cGroup; }

		//{ExtensionPointCS}
		public Action getExtensionPointCSAction_0() { return cExtensionPointCSAction_0; }

		//'extensionPoint'
		public Keyword getExtensionPointKeyword_1() { return cExtensionPointKeyword_1; }

		//name=UnrestrictedName?
		public Assignment getNameAssignment_2() { return cNameAssignment_2; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_2_0() { return cNameUnrestrictedNameParserRuleCall_2_0; }

		//('at' '{' locations+=StepRefCS ('|' locations+=StepRefCS)* '}')?
		public Group getGroup_3() { return cGroup_3; }

		//'at'
		public Keyword getAtKeyword_3_0() { return cAtKeyword_3_0; }

		//'{'
		public Keyword getLeftCurlyBracketKeyword_3_1() { return cLeftCurlyBracketKeyword_3_1; }

		//locations+=StepRefCS
		public Assignment getLocationsAssignment_3_2() { return cLocationsAssignment_3_2; }

		//StepRefCS
		public RuleCall getLocationsStepRefCSParserRuleCall_3_2_0() { return cLocationsStepRefCSParserRuleCall_3_2_0; }

		//('|' locations+=StepRefCS)*
		public Group getGroup_3_3() { return cGroup_3_3; }

		//'|'
		public Keyword getVerticalLineKeyword_3_3_0() { return cVerticalLineKeyword_3_3_0; }

		//locations+=StepRefCS
		public Assignment getLocationsAssignment_3_3_1() { return cLocationsAssignment_3_3_1; }

		//StepRefCS
		public RuleCall getLocationsStepRefCSParserRuleCall_3_3_1_0() { return cLocationsStepRefCSParserRuleCall_3_3_1_0; }

		//'}'
		public Keyword getRightCurlyBracketKeyword_3_4() { return cRightCurlyBracketKeyword_3_4; }

		//('description' '=' description=StringLiteral)?
		public Group getGroup_4() { return cGroup_4; }

		//'description'
		public Keyword getDescriptionKeyword_4_0() { return cDescriptionKeyword_4_0; }

		//'='
		public Keyword getEqualsSignKeyword_4_1() { return cEqualsSignKeyword_4_1; }

		//description=StringLiteral
		public Assignment getDescriptionAssignment_4_2() { return cDescriptionAssignment_4_2; }

		//StringLiteral
		public RuleCall getDescriptionStringLiteralParserRuleCall_4_2_0() { return cDescriptionStringLiteralParserRuleCall_4_2_0; }

		//'when'
		public Keyword getWhenKeyword_5() { return cWhenKeyword_5; }

		//condition=SnapshotPatternCS?
		public Assignment getConditionAssignment_6() { return cConditionAssignment_6; }

		//SnapshotPatternCS
		public RuleCall getConditionSnapshotPatternCSParserRuleCall_6_0() { return cConditionSnapshotPatternCSParserRuleCall_6_0; }

		//'end'
		public Keyword getEndKeyword_7() { return cEndKeyword_7; }
	}

	public class UcExtendCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.UcExtendCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cExtensionAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cExtensionUsecaseRefCSParserRuleCall_0_0 = (RuleCall)cExtensionAssignment_0.eContents().get(0);
		private final Keyword cExtendsKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cExtendedUCAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cExtendedUCUsecaseRefCSParserRuleCall_2_0 = (RuleCall)cExtendedUCAssignment_2.eContents().get(0);
		private final Keyword cAtKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Keyword cLeftCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cExtPointsAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cExtPointsExtensionPointRefCSParserRuleCall_5_0 = (RuleCall)cExtPointsAssignment_5.eContents().get(0);
		private final Group cGroup_6 = (Group)cGroup.eContents().get(6);
		private final Keyword cVerticalLineKeyword_6_0 = (Keyword)cGroup_6.eContents().get(0);
		private final Assignment cExtPointsAssignment_6_1 = (Assignment)cGroup_6.eContents().get(1);
		private final RuleCall cExtPointsExtensionPointRefCSParserRuleCall_6_1_0 = (RuleCall)cExtPointsAssignment_6_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_7 = (Keyword)cGroup.eContents().get(7);

		//UcExtendCS ExtendCS:
		//	extension=UsecaseRefCS 'extends' extendedUC=UsecaseRefCS 'at' '{' extPoints+=ExtensionPointRefCS ('|'
		//	extPoints+=ExtensionPointRefCS)* '}';
		@Override public ParserRule getRule() { return rule; }

		//extension=UsecaseRefCS 'extends' extendedUC=UsecaseRefCS 'at' '{' extPoints+=ExtensionPointRefCS ('|'
		//extPoints+=ExtensionPointRefCS)* '}'
		public Group getGroup() { return cGroup; }

		//extension=UsecaseRefCS
		public Assignment getExtensionAssignment_0() { return cExtensionAssignment_0; }

		//UsecaseRefCS
		public RuleCall getExtensionUsecaseRefCSParserRuleCall_0_0() { return cExtensionUsecaseRefCSParserRuleCall_0_0; }

		//'extends'
		public Keyword getExtendsKeyword_1() { return cExtendsKeyword_1; }

		//extendedUC=UsecaseRefCS
		public Assignment getExtendedUCAssignment_2() { return cExtendedUCAssignment_2; }

		//UsecaseRefCS
		public RuleCall getExtendedUCUsecaseRefCSParserRuleCall_2_0() { return cExtendedUCUsecaseRefCSParserRuleCall_2_0; }

		//'at'
		public Keyword getAtKeyword_3() { return cAtKeyword_3; }

		//'{'
		public Keyword getLeftCurlyBracketKeyword_4() { return cLeftCurlyBracketKeyword_4; }

		//extPoints+=ExtensionPointRefCS
		public Assignment getExtPointsAssignment_5() { return cExtPointsAssignment_5; }

		//ExtensionPointRefCS
		public RuleCall getExtPointsExtensionPointRefCSParserRuleCall_5_0() { return cExtPointsExtensionPointRefCSParserRuleCall_5_0; }

		//('|' extPoints+=ExtensionPointRefCS)*
		public Group getGroup_6() { return cGroup_6; }

		//'|'
		public Keyword getVerticalLineKeyword_6_0() { return cVerticalLineKeyword_6_0; }

		//extPoints+=ExtensionPointRefCS
		public Assignment getExtPointsAssignment_6_1() { return cExtPointsAssignment_6_1; }

		//ExtensionPointRefCS
		public RuleCall getExtPointsExtensionPointRefCSParserRuleCall_6_1_0() { return cExtPointsExtensionPointRefCSParserRuleCall_6_1_0; }

		//'}'
		public Keyword getRightCurlyBracketKeyword_7() { return cRightCurlyBracketKeyword_7; }
	}

	public class AssocEndCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.AssocEndCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameUnrestrictedNameParserRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cColonKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cOwnedTypeAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cOwnedTypeTypedTupeMultiplicityRefCSParserRuleCall_2_0 = (RuleCall)cOwnedTypeAssignment_2.eContents().get(0);

		//AssocEndCS base::ReferenceCS:
		//	name=UnrestrictedName ':' ownedType=TypedTupeMultiplicityRefCS;
		@Override public ParserRule getRule() { return rule; }

		//name=UnrestrictedName ':' ownedType=TypedTupeMultiplicityRefCS
		public Group getGroup() { return cGroup; }

		//name=UnrestrictedName
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }

		//UnrestrictedName
		public RuleCall getNameUnrestrictedNameParserRuleCall_0_0() { return cNameUnrestrictedNameParserRuleCall_0_0; }

		//':'
		public Keyword getColonKeyword_1() { return cColonKeyword_1; }

		//ownedType=TypedTupeMultiplicityRefCS
		public Assignment getOwnedTypeAssignment_2() { return cOwnedTypeAssignment_2; }

		//TypedTupeMultiplicityRefCS
		public RuleCall getOwnedTypeTypedTupeMultiplicityRefCSParserRuleCall_2_0() { return cOwnedTypeTypedTupeMultiplicityRefCSParserRuleCall_2_0; }
	}

	public class TypedTupeMultiplicityRefCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.TypedTupeMultiplicityRefCS");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cTypedTypeRefCSParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Assignment cOwnedMultiplicityAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cOwnedMultiplicityMultiplicityCSParserRuleCall_1_0 = (RuleCall)cOwnedMultiplicityAssignment_1.eContents().get(0);

		//TypedTupeMultiplicityRefCS base::TypedRefCS:
		//	TypedTypeRefCS ownedMultiplicity=MultiplicityCS?;
		@Override public ParserRule getRule() { return rule; }

		//TypedTypeRefCS ownedMultiplicity=MultiplicityCS?
		public Group getGroup() { return cGroup; }

		//TypedTypeRefCS
		public RuleCall getTypedTypeRefCSParserRuleCall_0() { return cTypedTypeRefCSParserRuleCall_0; }

		//ownedMultiplicity=MultiplicityCS?
		public Assignment getOwnedMultiplicityAssignment_1() { return cOwnedMultiplicityAssignment_1; }

		//MultiplicityCS
		public RuleCall getOwnedMultiplicityMultiplicityCSParserRuleCall_1_0() { return cOwnedMultiplicityMultiplicityCSParserRuleCall_1_0; }
	}

	public class ActorRefCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ActorRefCS");
		private final Assignment cOwnedPathNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cOwnedPathNamePathNameCSParserRuleCall_0 = (RuleCall)cOwnedPathNameAssignment.eContents().get(0);

		//ActorRefCS base::ModelElementRefCS:
		//	ownedPathName=PathNameCS;
		@Override public ParserRule getRule() { return rule; }

		//ownedPathName=PathNameCS
		public Assignment getOwnedPathNameAssignment() { return cOwnedPathNameAssignment; }

		//PathNameCS
		public RuleCall getOwnedPathNamePathNameCSParserRuleCall_0() { return cOwnedPathNamePathNameCSParserRuleCall_0; }
	}

	public class UsecaseRefCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.UsecaseRefCS");
		private final Assignment cOwnedPathNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cOwnedPathNamePathNameCSParserRuleCall_0 = (RuleCall)cOwnedPathNameAssignment.eContents().get(0);

		//UsecaseRefCS base::ModelElementRefCS:
		//	ownedPathName=PathNameCS;
		@Override public ParserRule getRule() { return rule; }

		//ownedPathName=PathNameCS
		public Assignment getOwnedPathNameAssignment() { return cOwnedPathNameAssignment; }

		//PathNameCS
		public RuleCall getOwnedPathNamePathNameCSParserRuleCall_0() { return cOwnedPathNamePathNameCSParserRuleCall_0; }
	}

	public class StepRefCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.StepRefCS");
		private final Assignment cOwnedPathNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cOwnedPathNamePathNameCSParserRuleCall_0 = (RuleCall)cOwnedPathNameAssignment.eContents().get(0);

		//StepRefCS base::ModelElementRefCS:
		//	ownedPathName=PathNameCS;
		@Override public ParserRule getRule() { return rule; }

		//ownedPathName=PathNameCS
		public Assignment getOwnedPathNameAssignment() { return cOwnedPathNameAssignment; }

		//PathNameCS
		public RuleCall getOwnedPathNamePathNameCSParserRuleCall_0() { return cOwnedPathNamePathNameCSParserRuleCall_0; }
	}

	public class ExtensionPointRefCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ExtensionPointRefCS");
		private final Assignment cOwnedPathNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cOwnedPathNamePathNameCSParserRuleCall_0 = (RuleCall)cOwnedPathNameAssignment.eContents().get(0);

		//ExtensionPointRefCS base::ModelElementRefCS:
		//	ownedPathName=PathNameCS;
		@Override public ParserRule getRule() { return rule; }

		//ownedPathName=PathNameCS
		public Assignment getOwnedPathNameAssignment() { return cOwnedPathNameAssignment; }

		//PathNameCS
		public RuleCall getOwnedPathNamePathNameCSParserRuleCall_0() { return cOwnedPathNamePathNameCSParserRuleCall_0; }
	}

	public class ObjVarRefCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.ObjVarRefCS");
		private final Assignment cOwnedPathNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cOwnedPathNamePathNameCSParserRuleCall_0 = (RuleCall)cOwnedPathNameAssignment.eContents().get(0);

		//ObjVarRefCS base::ModelElementRefCS:
		//	ownedPathName=PathNameCS;
		@Override public ParserRule getRule() { return rule; }

		//ownedPathName=PathNameCS
		public Assignment getOwnedPathNameAssignment() { return cOwnedPathNameAssignment; }

		//PathNameCS
		public RuleCall getOwnedPathNamePathNameCSParserRuleCall_0() { return cOwnedPathNamePathNameCSParserRuleCall_0; }
	}

	public class AssocRefCSElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.eclipse.sme.frsl.FRSL.AssocRefCS");
		private final Assignment cOwnedPathNameAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cOwnedPathNamePathNameCSParserRuleCall_0 = (RuleCall)cOwnedPathNameAssignment.eContents().get(0);

		//AssocRefCS base::ModelElementRefCS:
		//	ownedPathName=PathNameCS;
		@Override public ParserRule getRule() { return rule; }

		//ownedPathName=PathNameCS
		public Assignment getOwnedPathNameAssignment() { return cOwnedPathNameAssignment; }

		//PathNameCS
		public RuleCall getOwnedPathNamePathNameCSParserRuleCall_0() { return cOwnedPathNamePathNameCSParserRuleCall_0; }
	}


	private final FrslModelCSElements pFrslModelCS;
	private final UsecaseCSElements pUsecaseCS;
	private final UsecasePreconditionCSElements pUsecasePreconditionCS;
	private final UsecasePostconditionCSElements pUsecasePostconditionCS;
	private final StepCSElements pStepCS;
	private final ActStepCSElements pActStepCS;
	private final ActorStepCSElements pActorStepCS;
	private final SystemStepCSElements pSystemStepCS;
	private final ActorActionCSElements pActorActionCS;
	private final SystemActionCSElements pSystemActionCS;
	private final InputActionCSElements pInputActionCS;
	private final OutputActionCSElements pOutputActionCS;
	private final RejoinStepCSElements pRejoinStepCS;
	private final UCStepCSElements pUCStepCS;
	private final AltFlowCSElements pAltFlowCS;
	private final SnapshotPatternCSElements pSnapshotPatternCS;
	private final ObjVarCSElements pObjVarCS;
	private final NegObjVarCSElements pNegObjVarCS;
	private final VarLinkCSElements pVarLinkCS;
	private final AssociationCSElements pAssociationCS;
	private final ConstraintCSElements pConstraintCS;
	private final ActorCSElements pActorCS;
	private final ExtensionPointCSElements pExtensionPointCS;
	private final UcExtendCSElements pUcExtendCS;
	private final AssocEndCSElements pAssocEndCS;
	private final TypedTupeMultiplicityRefCSElements pTypedTupeMultiplicityRefCS;
	private final ActorRefCSElements pActorRefCS;
	private final UsecaseRefCSElements pUsecaseRefCS;
	private final StepRefCSElements pStepRefCS;
	private final ExtensionPointRefCSElements pExtensionPointRefCS;
	private final ObjVarRefCSElements pObjVarRefCS;
	private final AssocRefCSElements pAssocRefCS;

	private final Grammar grammar;

	private final OCLinEcoreGrammarAccess gaOCLinEcore;

	private final EssentialOCLGrammarAccess gaEssentialOCL;

	private final BaseGrammarAccess gaBase;

	@Inject
	public FRSLGrammarAccess(GrammarProvider grammarProvider,
		OCLinEcoreGrammarAccess gaOCLinEcore,
		EssentialOCLGrammarAccess gaEssentialOCL,
		BaseGrammarAccess gaBase) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaOCLinEcore = gaOCLinEcore;
		this.gaEssentialOCL = gaEssentialOCL;
		this.gaBase = gaBase;
		this.pFrslModelCS = new FrslModelCSElements();
		this.pUsecaseCS = new UsecaseCSElements();
		this.pUsecasePreconditionCS = new UsecasePreconditionCSElements();
		this.pUsecasePostconditionCS = new UsecasePostconditionCSElements();
		this.pStepCS = new StepCSElements();
		this.pActStepCS = new ActStepCSElements();
		this.pActorStepCS = new ActorStepCSElements();
		this.pSystemStepCS = new SystemStepCSElements();
		this.pActorActionCS = new ActorActionCSElements();
		this.pSystemActionCS = new SystemActionCSElements();
		this.pInputActionCS = new InputActionCSElements();
		this.pOutputActionCS = new OutputActionCSElements();
		this.pRejoinStepCS = new RejoinStepCSElements();
		this.pUCStepCS = new UCStepCSElements();
		this.pAltFlowCS = new AltFlowCSElements();
		this.pSnapshotPatternCS = new SnapshotPatternCSElements();
		this.pObjVarCS = new ObjVarCSElements();
		this.pNegObjVarCS = new NegObjVarCSElements();
		this.pVarLinkCS = new VarLinkCSElements();
		this.pAssociationCS = new AssociationCSElements();
		this.pConstraintCS = new ConstraintCSElements();
		this.pActorCS = new ActorCSElements();
		this.pExtensionPointCS = new ExtensionPointCSElements();
		this.pUcExtendCS = new UcExtendCSElements();
		this.pAssocEndCS = new AssocEndCSElements();
		this.pTypedTupeMultiplicityRefCS = new TypedTupeMultiplicityRefCSElements();
		this.pActorRefCS = new ActorRefCSElements();
		this.pUsecaseRefCS = new UsecaseRefCSElements();
		this.pStepRefCS = new StepRefCSElements();
		this.pExtensionPointRefCS = new ExtensionPointRefCSElements();
		this.pObjVarRefCS = new ObjVarRefCSElements();
		this.pAssocRefCS = new AssocRefCSElements();
	}

	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.eclipse.sme.frsl.FRSL".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}

	@Override
	public Grammar getGrammar() {
		return grammar;
	}


	public OCLinEcoreGrammarAccess getOCLinEcoreGrammarAccess() {
		return gaOCLinEcore;
	}

	public EssentialOCLGrammarAccess getEssentialOCLGrammarAccess() {
		return gaEssentialOCL;
	}

	public BaseGrammarAccess getBaseGrammarAccess() {
		return gaBase;
	}


	////generate frsl "http://www.eclipse.org/sme/frsl/Frsl"
	//FrslModelCS:
	//	domainModel=TopLevelCS
	//	assocs+=AssociationCS*
	//	actors+=ActorCS*
	//	usecases+=UsecaseCS*
	//	ucExtends+=UcExtendCS*;
	public FrslModelCSElements getFrslModelCSAccess() {
		return pFrslModelCS;
	}

	public ParserRule getFrslModelCSRule() {
		return getFrslModelCSAccess().getRule();
	}

	//UsecaseCS:
	//	'usecase' name=UnrestrictedName ('description' '=' description=StringLiteral)?
	//	'primaryActor' '=' primaryActor=ActorRefCS ('secondaryActors' '=' '{' secondaryActors+=ActorRefCS (','
	//	secondaryActors+=ActorRefCS)* '}')?
	//	precondition=UsecasePreconditionCS?
	//	postcondition=UsecasePostconditionCS?
	//	firstStep=StepCS
	//	extPoints+=ExtensionPointCS*
	//	'end';
	public UsecaseCSElements getUsecaseCSAccess() {
		return pUsecaseCS;
	}

	public ParserRule getUsecaseCSRule() {
		return getUsecaseCSAccess().getRule();
	}

	//UsecasePreconditionCS:
	//	'ucPrecondition' ('description' '=' description=StringLiteral)?
	//	snapshot=SnapshotPatternCS
	//	'end';
	public UsecasePreconditionCSElements getUsecasePreconditionCSAccess() {
		return pUsecasePreconditionCS;
	}

	public ParserRule getUsecasePreconditionCSRule() {
		return getUsecasePreconditionCSAccess().getRule();
	}

	//UsecasePostconditionCS:
	//	'ucPostcondition' ('description' '=' description=StringLiteral)?
	//	snapshot=SnapshotPatternCS
	//	'end';
	public UsecasePostconditionCSElements getUsecasePostconditionCSAccess() {
		return pUsecasePostconditionCS;
	}

	public ParserRule getUsecasePostconditionCSRule() {
		return getUsecasePostconditionCSAccess().getRule();
	}

	//StepCS:
	//	(ActStepCS | RejoinStepCS | UCStepCS) altFlows+=AltFlowCS*
	//	nextStep=StepCS?;
	public StepCSElements getStepCSAccess() {
		return pStepCS;
	}

	public ParserRule getStepCSRule() {
		return getStepCSAccess().getRule();
	}

	//ActStepCS:
	//	ActorStepCS | SystemStepCS;
	public ActStepCSElements getActStepCSAccess() {
		return pActStepCS;
	}

	public ParserRule getActStepCSRule() {
		return getActStepCSAccess().getRule();
	}

	//ActorStepCS ActStepCS:
	//	isActorStep?='actStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
	//	'from'
	//	preSnapshot=SnapshotPatternCS
	//	'to'
	//	postSnapshot=SnapshotPatternCS ('actions' actions+=ActorActionCS*)?
	//	'end';
	public ActorStepCSElements getActorStepCSAccess() {
		return pActorStepCS;
	}

	public ParserRule getActorStepCSRule() {
		return getActorStepCSAccess().getRule();
	}

	//SystemStepCS ActStepCS:
	//	'sysStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
	//	'from'
	//	preSnapshot=SnapshotPatternCS
	//	'to'
	//	postSnapshot=SnapshotPatternCS ('actions' actions+=SystemActionCS*)?
	//	'end';
	public SystemStepCSElements getSystemStepCSAccess() {
		return pSystemStepCS;
	}

	public ParserRule getSystemStepCSRule() {
		return getSystemStepCSAccess().getRule();
	}

	//ActorActionCS:
	//	InputActionCS;
	public ActorActionCSElements getActorActionCSAccess() {
		return pActorActionCS;
	}

	public ParserRule getActorActionCSRule() {
		return getActorActionCSAccess().getRule();
	}

	//SystemActionCS:
	//	OutputActionCS;
	public SystemActionCSElements getSystemActionCSAccess() {
		return pSystemActionCS;
	}

	public ParserRule getSystemActionCSRule() {
		return getSystemActionCSAccess().getRule();
	}

	//InputActionCS ActorActionCS:
	//	actor=ActorRefCS? '->' (objVars+=ObjVarCS ';')+;
	public InputActionCSElements getInputActionCSAccess() {
		return pInputActionCS;
	}

	public ParserRule getInputActionCSRule() {
		return getInputActionCSAccess().getRule();
	}

	//OutputActionCS SystemActionCS:
	//	actor=ActorRefCS '<-' (objVars+=ObjVarCS '=' values+=SpecificationCS ';')+;
	public OutputActionCSElements getOutputActionCSAccess() {
		return pOutputActionCS;
	}

	public ParserRule getOutputActionCSRule() {
		return getOutputActionCSAccess().getRule();
	}

	//RejoinStepCS:
	//	'rejoinStep' rejoinTo=StepRefCS ('description' '=' description=StringLiteral)? ('when'
	//	condition=SnapshotPatternCS)?
	//	'end';
	public RejoinStepCSElements getRejoinStepCSAccess() {
		return pRejoinStepCS;
	}

	public ParserRule getRejoinStepCSRule() {
		return getRejoinStepCSAccess().getRule();
	}

	//UCStepCS:
	//	'inclStep' name=UnrestrictedName? ('description' '=' description=StringLiteral)?
	//	includedUC=UsecaseRefCS
	//	'end';
	public UCStepCSElements getUCStepCSAccess() {
		return pUCStepCS;
	}

	public ParserRule getUCStepCSRule() {
		return getUCStepCSAccess().getRule();
	}

	//AltFlowCS:
	//	'altStep' name=UnrestrictedName? 'at' baseStep=StepRefCS ('description' '=' description=StringLiteral)?
	//	'when'
	//	condition=SnapshotPatternCS
	//	'end'
	//	altStep=StepCS
	//	'end';
	public AltFlowCSElements getAltFlowCSAccess() {
		return pAltFlowCS;
	}

	public ParserRule getAltFlowCSRule() {
		return getAltFlowCSAccess().getRule();
	}

	//SnapshotPatternCS:
	//	{SnapshotPatternCS} ('snapshotID' '=' name=UnrestrictedName)? ('description' '=' description=StringLiteral)? (
	//	(objects+=ObjVarCS | negObjects+=NegObjVarCS) ';')* (links+=VarLinkCS ';')*
	//	constraints+=ConstraintCS*;
	public SnapshotPatternCSElements getSnapshotPatternCSAccess() {
		return pSnapshotPatternCS;
	}

	public ParserRule getSnapshotPatternCSRule() {
		return getSnapshotPatternCSAccess().getRule();
	}

	//ObjVarCS:
	//	isMatched?='$'? name=UnrestrictedName ':' type=TypedRefCS;
	public ObjVarCSElements getObjVarCSAccess() {
		return pObjVarCS;
	}

	public ParserRule getObjVarCSRule() {
		return getObjVarCSAccess().getRule();
	}

	//NegObjVarCS base::ModelElementRefCS:
	//	'!' ObjVarRefCS;
	public NegObjVarCSElements getNegObjVarCSAccess() {
		return pNegObjVarCS;
	}

	public ParserRule getNegObjVarCSRule() {
		return getNegObjVarCSAccess().getRule();
	}

	//VarLinkCS:
	//	isNeg?='!'? '(' objVars+=ObjVarRefCS ',' objVars+=ObjVarRefCS ')' ':' assoc=AssocRefCS;
	public VarLinkCSElements getVarLinkCSAccess() {
		return pVarLinkCS;
	}

	public ParserRule getVarLinkCSRule() {
		return getVarLinkCSAccess().getRule();
	}

	//AssociationCS:
	//	'association' name=UnrestrictedName
	//	assocEnds+=AssocEndCS
	//	assocEnds+=AssocEndCS
	//	'end';
	public AssociationCSElements getAssociationCSAccess() {
		return pAssociationCS;
	}

	public ParserRule getAssociationCSRule() {
		return getAssociationCSAccess().getRule();
	}

	//ConstraintCS:
	//	'[' constrExpr=SpecificationCS ']';
	public ConstraintCSElements getConstraintCSAccess() {
		return pConstraintCS;
	}

	public ParserRule getConstraintCSRule() {
		return getConstraintCSAccess().getRule();
	}

	//ActorCS:
	//	'actor' name=UnrestrictedName ('description' '=' description=StringLiteral)?
	//	'end';
	public ActorCSElements getActorCSAccess() {
		return pActorCS;
	}

	public ParserRule getActorCSRule() {
		return getActorCSAccess().getRule();
	}

	//ExtensionPointCS:
	//	{ExtensionPointCS} 'extensionPoint' name=UnrestrictedName? ('at' '{' locations+=StepRefCS ('|' locations+=StepRefCS)
	//	* '}')? ('description' '=' description=StringLiteral)?
	//	'when'
	//	condition=SnapshotPatternCS?
	//	'end';
	public ExtensionPointCSElements getExtensionPointCSAccess() {
		return pExtensionPointCS;
	}

	public ParserRule getExtensionPointCSRule() {
		return getExtensionPointCSAccess().getRule();
	}

	//UcExtendCS ExtendCS:
	//	extension=UsecaseRefCS 'extends' extendedUC=UsecaseRefCS 'at' '{' extPoints+=ExtensionPointRefCS ('|'
	//	extPoints+=ExtensionPointRefCS)* '}';
	public UcExtendCSElements getUcExtendCSAccess() {
		return pUcExtendCS;
	}

	public ParserRule getUcExtendCSRule() {
		return getUcExtendCSAccess().getRule();
	}

	//AssocEndCS base::ReferenceCS:
	//	name=UnrestrictedName ':' ownedType=TypedTupeMultiplicityRefCS;
	public AssocEndCSElements getAssocEndCSAccess() {
		return pAssocEndCS;
	}

	public ParserRule getAssocEndCSRule() {
		return getAssocEndCSAccess().getRule();
	}

	//TypedTupeMultiplicityRefCS base::TypedRefCS:
	//	TypedTypeRefCS ownedMultiplicity=MultiplicityCS?;
	public TypedTupeMultiplicityRefCSElements getTypedTupeMultiplicityRefCSAccess() {
		return pTypedTupeMultiplicityRefCS;
	}

	public ParserRule getTypedTupeMultiplicityRefCSRule() {
		return getTypedTupeMultiplicityRefCSAccess().getRule();
	}

	//ActorRefCS base::ModelElementRefCS:
	//	ownedPathName=PathNameCS;
	public ActorRefCSElements getActorRefCSAccess() {
		return pActorRefCS;
	}

	public ParserRule getActorRefCSRule() {
		return getActorRefCSAccess().getRule();
	}

	//UsecaseRefCS base::ModelElementRefCS:
	//	ownedPathName=PathNameCS;
	public UsecaseRefCSElements getUsecaseRefCSAccess() {
		return pUsecaseRefCS;
	}

	public ParserRule getUsecaseRefCSRule() {
		return getUsecaseRefCSAccess().getRule();
	}

	//StepRefCS base::ModelElementRefCS:
	//	ownedPathName=PathNameCS;
	public StepRefCSElements getStepRefCSAccess() {
		return pStepRefCS;
	}

	public ParserRule getStepRefCSRule() {
		return getStepRefCSAccess().getRule();
	}

	//ExtensionPointRefCS base::ModelElementRefCS:
	//	ownedPathName=PathNameCS;
	public ExtensionPointRefCSElements getExtensionPointRefCSAccess() {
		return pExtensionPointRefCS;
	}

	public ParserRule getExtensionPointRefCSRule() {
		return getExtensionPointRefCSAccess().getRule();
	}

	//ObjVarRefCS base::ModelElementRefCS:
	//	ownedPathName=PathNameCS;
	public ObjVarRefCSElements getObjVarRefCSAccess() {
		return pObjVarRefCS;
	}

	public ParserRule getObjVarRefCSRule() {
		return getObjVarRefCSAccess().getRule();
	}

	//AssocRefCS base::ModelElementRefCS:
	//	ownedPathName=PathNameCS;
	public AssocRefCSElements getAssocRefCSAccess() {
		return pAssocRefCS;
	}

	public ParserRule getAssocRefCSRule() {
		return getAssocRefCSAccess().getRule();
	}

	////generate oclinEcore2 "http://www.eclipse.org/ocl/examples/xtext/oclinecore/OCLinEcore"
	//TopLevelCS:
	//	{TopLevelCS} ('module' UnrestrictedName)?
	//	ownedImports+=ImportCS*
	//	ownedPackages+=PackageCS*;
	public OCLinEcoreGrammarAccess.TopLevelCSElements getTopLevelCSAccess() {
		return gaOCLinEcore.getTopLevelCSAccess();
	}

	public ParserRule getTopLevelCSRule() {
		return getTopLevelCSAccess().getRule();
	}

	//terminal UNQUOTED_STRING: // Never forward parsed; just provides a placeholder
	//	'£$%^£$%^' // for reverse serialisation of embedded OCL
	//;
	public TerminalRule getUNQUOTED_STRINGRule() {
		return gaOCLinEcore.getUNQUOTED_STRINGRule();
	}

	//INTEGER ecore::EInt:
	//	INT;
	public OCLinEcoreGrammarAccess.INTEGERElements getINTEGERAccess() {
		return gaOCLinEcore.getINTEGERAccess();
	}

	public ParserRule getINTEGERRule() {
		return getINTEGERAccess().getRule();
	}

	//SIGNED ecore::EInt:
	//	'-'? INT;
	public OCLinEcoreGrammarAccess.SIGNEDElements getSIGNEDAccess() {
		return gaOCLinEcore.getSIGNEDAccess();
	}

	public ParserRule getSIGNEDRule() {
		return getSIGNEDAccess().getRule();
	}

	//EnumerationLiteralName:
	//	EssentialOCLUnrestrictedName
	//	| 'abstract'
	//	| 'attribute'
	//	| 'body'
	//	| 'callable'
	//	| 'class'
	//	| 'composes'
	//	| 'datatype'
	//	| 'definition'
	//	| 'derivation'
	//	| 'derived'
	//	| 'enum'
	//	| 'extends'
	//	| 'id'
	//	| 'import'
	//	| 'initial'
	//	| 'interface'
	//	| 'key'
	//	| 'library'
	//	| 'module'
	//	| 'operation'
	//	| 'ordered'
	//	| 'package'
	//	| 'postcondition'
	//	| 'precondition'
	//	| 'primitive'
	//	| 'property'
	//	| 'readonly'
	//	| 'reference'
	//	| 'resolve'
	//	| 'static'
	//	| 'throws'
	//	| 'transient'
	//	| 'unique'
	//	| 'unsettable'
	//	| 'volatile';
	public OCLinEcoreGrammarAccess.EnumerationLiteralNameElements getEnumerationLiteralNameAccess() {
		return gaOCLinEcore.getEnumerationLiteralNameAccess();
	}

	public ParserRule getEnumerationLiteralNameRule() {
		return getEnumerationLiteralNameAccess().getRule();
	}

	//InvariantConstraintCS OCLinEcoreConstraintCS:
	//	isCallable?='callable'? stereotype='invariant' (name=UnrestrictedName ('(' ownedMessageSpecification=SpecificationCS
	//	')')?)? (':' ownedSpecification=SpecificationCS? ';' | ';');
	public OCLinEcoreGrammarAccess.InvariantConstraintCSElements getInvariantConstraintCSAccess() {
		return gaOCLinEcore.getInvariantConstraintCSAccess();
	}

	public ParserRule getInvariantConstraintCSRule() {
		return getInvariantConstraintCSAccess().getRule();
	}

	//PostconditionConstraintCS OCLinEcoreConstraintCS:
	//	stereotype='postcondition' (name=UnrestrictedName ('(' ownedMessageSpecification=SpecificationCS ')')?)? ':'
	//	ownedSpecification=SpecificationCS? ';';
	public OCLinEcoreGrammarAccess.PostconditionConstraintCSElements getPostconditionConstraintCSAccess() {
		return gaOCLinEcore.getPostconditionConstraintCSAccess();
	}

	public ParserRule getPostconditionConstraintCSRule() {
		return getPostconditionConstraintCSAccess().getRule();
	}

	//PreconditionConstraintCS OCLinEcoreConstraintCS:
	//	stereotype='precondition' (name=UnrestrictedName ('(' ownedMessageSpecification=SpecificationCS ')')?)? ':'
	//	ownedSpecification=SpecificationCS? ';';
	public OCLinEcoreGrammarAccess.PreconditionConstraintCSElements getPreconditionConstraintCSAccess() {
		return gaOCLinEcore.getPreconditionConstraintCSAccess();
	}

	public ParserRule getPreconditionConstraintCSRule() {
		return getPreconditionConstraintCSAccess().getRule();
	}

	//AnnotationCS base::AnnotationCS:
	//	{base::AnnotationCS} 'annotation' name=(UnrestrictedName | SINGLE_QUOTED_STRING)? ('(' ownedDetails+=DetailCS (','
	//	ownedDetails+=DetailCS)* ')')? ('{' (ownedAnnotations+=AnnotationElementCS
	//	| ownedContents+=ModelElementCS
	//	| ownedReferences+=ModelElementRefCS)+ '}' | ';');
	public OCLinEcoreGrammarAccess.AnnotationCSElements getAnnotationCSAccess() {
		return gaOCLinEcore.getAnnotationCSAccess();
	}

	public ParserRule getAnnotationCSRule() {
		return getAnnotationCSAccess().getRule();
	}

	//AnnotationElementCS base::AnnotationElementCS:
	//	AnnotationCS | DocumentationCS | SysMLCS;
	public OCLinEcoreGrammarAccess.AnnotationElementCSElements getAnnotationElementCSAccess() {
		return gaOCLinEcore.getAnnotationElementCSAccess();
	}

	public ParserRule getAnnotationElementCSRule() {
		return getAnnotationElementCSAccess().getRule();
	}

	//AttributeCS base::AttributeCS:
	//	(qualifiers+='static' qualifiers+='definition'? | qualifiers+='definition' qualifiers+='static'?)?
	//	'attribute' name=UnrestrictedName (':' ownedType=TypedMultiplicityRefCS)? ('=' default=SINGLE_QUOTED_STRING)? ('{' (
	//	(qualifiers+='derived' | qualifiers+='!derived' | qualifiers+='id' | qualifiers+='!id' | qualifiers+='ordered' |
	//	qualifiers+='!ordered' | qualifiers+='readonly' | qualifiers+='!readonly' | qualifiers+='transient' | qualifiers+=
	//	'!transient' | qualifiers+='unique' | qualifiers+='!unique' | qualifiers+='unsettable' | qualifiers+='!unsettable' |
	//	qualifiers+='volatile' | qualifiers+='!volatile') ','?)+
	//	'}')? ('{' (ownedAnnotations+=AnnotationElementCS
	//	| 'initial' UnrestrictedName? ':' ownedDefaultExpressions+=SpecificationCS? ';' | 'derivation' UnrestrictedName? ':'
	//	ownedDefaultExpressions+=SpecificationCS? ';')* '}' | ';');
	public OCLinEcoreGrammarAccess.AttributeCSElements getAttributeCSAccess() {
		return gaOCLinEcore.getAttributeCSAccess();
	}

	public ParserRule getAttributeCSRule() {
		return getAttributeCSAccess().getRule();
	}

	//ClassCS base::ClassCS:
	//	StructuredClassCS | DataTypeCS | EnumerationCS;
	public OCLinEcoreGrammarAccess.ClassCSElements getClassCSAccess() {
		return gaOCLinEcore.getClassCSAccess();
	}

	public ParserRule getClassCSRule() {
		return getClassCSAccess().getRule();
	}

	//DataTypeCS base::DataTypeCS:
	//	isPrimitive?='primitive'? 'datatype' name=UnrestrictedName
	//	ownedSignature=TemplateSignatureCS? (':' instanceClassName=SINGLE_QUOTED_STRING)? ('{' (isSerializable?=
	//	'serializable' | '!serializable')? '}')? ('{' (ownedAnnotations+=AnnotationElementCS
	//	| ownedConstraints+=InvariantConstraintCS)* '}' | ';');
	public OCLinEcoreGrammarAccess.DataTypeCSElements getDataTypeCSAccess() {
		return gaOCLinEcore.getDataTypeCSAccess();
	}

	public ParserRule getDataTypeCSRule() {
		return getDataTypeCSAccess().getRule();
	}

	//DetailCS base::DetailCS:
	//	name=(UnrestrictedName | SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING | ML_SINGLE_QUOTED_STRING)*;
	public OCLinEcoreGrammarAccess.DetailCSElements getDetailCSAccess() {
		return gaOCLinEcore.getDetailCSAccess();
	}

	public ParserRule getDetailCSRule() {
		return getDetailCSAccess().getRule();
	}

	//DocumentationCS base::DocumentationCS:
	//	{base::DocumentationCS} 'documentation' value=SINGLE_QUOTED_STRING? ('(' ownedDetails+=DetailCS (','
	//	ownedDetails+=DetailCS)* ')')?
	//	';';
	public OCLinEcoreGrammarAccess.DocumentationCSElements getDocumentationCSAccess() {
		return gaOCLinEcore.getDocumentationCSAccess();
	}

	public ParserRule getDocumentationCSRule() {
		return getDocumentationCSAccess().getRule();
	}

	//EnumerationCS base::EnumerationCS:
	//	'enum' name=UnrestrictedName
	//	ownedSignature=TemplateSignatureCS? (':' instanceClassName=SINGLE_QUOTED_STRING)? ('{' (isSerializable?=
	//	'serializable' | '!serializable')? '}')? ('{' (ownedAnnotations+=AnnotationElementCS
	//	| ownedLiterals+=EnumerationLiteralCS
	//	| ownedConstraints+=InvariantConstraintCS)* '}' | ';');
	public OCLinEcoreGrammarAccess.EnumerationCSElements getEnumerationCSAccess() {
		return gaOCLinEcore.getEnumerationCSAccess();
	}

	public ParserRule getEnumerationCSRule() {
		return getEnumerationCSAccess().getRule();
	}

	//EnumerationLiteralCS base::EnumerationLiteralCS:
	//	('literal' name=UnrestrictedName | name=EnumerationLiteralName) (':' literal=SINGLE_QUOTED_STRING)? ('=' value=SIGNED
	//	)? ('{' ownedAnnotations+=AnnotationElementCS* '}' | ';');
	public OCLinEcoreGrammarAccess.EnumerationLiteralCSElements getEnumerationLiteralCSAccess() {
		return gaOCLinEcore.getEnumerationLiteralCSAccess();
	}

	public ParserRule getEnumerationLiteralCSRule() {
		return getEnumerationLiteralCSAccess().getRule();
	}

	//ImportCS base::ImportCS:
	//	('import' | 'library') (name=UnrestrictedName ':')? ownedPathName=URIPathNameCS isAll?='::*'? ';';
	public OCLinEcoreGrammarAccess.ImportCSElements getImportCSAccess() {
		return gaOCLinEcore.getImportCSAccess();
	}

	public ParserRule getImportCSRule() {
		return getImportCSAccess().getRule();
	}

	//ModelElementCS base::ModelElementCS:
	//	ClassCS | EnumerationLiteralCS | OperationCS | PackageCS | StructuralFeatureCS;
	public OCLinEcoreGrammarAccess.ModelElementCSElements getModelElementCSAccess() {
		return gaOCLinEcore.getModelElementCSAccess();
	}

	public ParserRule getModelElementCSRule() {
		return getModelElementCSAccess().getRule();
	}

	//ModelElementRefCS base::ModelElementRefCS:
	//	'reference' ownedPathName=PathNameCS ';';
	public OCLinEcoreGrammarAccess.ModelElementRefCSElements getModelElementRefCSAccess() {
		return gaOCLinEcore.getModelElementRefCSAccess();
	}

	public ParserRule getModelElementRefCSRule() {
		return getModelElementRefCSAccess().getRule();
	}

	//OperationCS base::OperationCS:
	//	(qualifiers+='static' qualifiers+='definition'? | qualifiers+='definition' qualifiers+='static'?)?
	//	'operation' ownedSignature=TemplateSignatureCS? name=UnrestrictedName
	//	'(' (ownedParameters+=ParameterCS (',' ownedParameters+=ParameterCS)*)? ')' (':' ownedType=TypedMultiplicityRefCS)? (
	//	'throws' ownedExceptions+=TypedRefCS (',' ownedExceptions+=TypedRefCS)*)? ('{' ((qualifiers+='derived' | qualifiers+=
	//	'!derived' | qualifiers+='ordered' | qualifiers+='!ordered' | qualifiers+='transient' | qualifiers+='!transient' |
	//	qualifiers+='unique' | qualifiers+='!unique') ','?)+
	//	'}')? ('{' (ownedAnnotations+=AnnotationElementCS
	//	| ownedPreconditions+=PreconditionConstraintCS
	//	| 'body' UnrestrictedName? ':' ownedBodyExpressions+=SpecificationCS? ';' |
	//	ownedPostconditions+=PostconditionConstraintCS)* '}' | ';');
	public OCLinEcoreGrammarAccess.OperationCSElements getOperationCSAccess() {
		return gaOCLinEcore.getOperationCSAccess();
	}

	public ParserRule getOperationCSRule() {
		return getOperationCSAccess().getRule();
	}

	//PackageCS base::PackageCS:
	//	'package' name=UnrestrictedName (':' nsPrefix=UnrestrictedName)? ('=' nsURI=URI)? ('{'
	//	(ownedAnnotations+=AnnotationElementCS | ownedPackages+=PackageCS | ownedClasses+=ClassCS)*
	//	'}' | ';');
	public OCLinEcoreGrammarAccess.PackageCSElements getPackageCSAccess() {
		return gaOCLinEcore.getPackageCSAccess();
	}

	public ParserRule getPackageCSRule() {
		return getPackageCSAccess().getRule();
	}

	//ParameterCS base::ParameterCS:
	//	name=UnrestrictedName (':' ownedType=TypedMultiplicityRefCS)? ('{' ((qualifiers+='ordered' | qualifiers+='!ordered' |
	//	qualifiers+='unique' | qualifiers+='!unique') ','?)+
	//	'}')? ('{' ownedAnnotations+=AnnotationElementCS* '}')?;
	public OCLinEcoreGrammarAccess.ParameterCSElements getParameterCSAccess() {
		return gaOCLinEcore.getParameterCSAccess();
	}

	public ParserRule getParameterCSRule() {
		return getParameterCSAccess().getRule();
	}

	//ImplicitOppositeCS base::ImplicitOppositeCS:
	//	'opposite' name=UnrestrictedName
	//	':' ownedType=TypedMultiplicityRefCS ('{' ((qualifiers+='ordered' | qualifiers+='!ordered' | qualifiers+='unique' |
	//	qualifiers+='!unique') ','?)+
	//	'}')?;
	public OCLinEcoreGrammarAccess.ImplicitOppositeCSElements getImplicitOppositeCSAccess() {
		return gaOCLinEcore.getImplicitOppositeCSAccess();
	}

	public ParserRule getImplicitOppositeCSRule() {
		return getImplicitOppositeCSAccess().getRule();
	}

	//ReferenceCS base::ReferenceCS:
	//	(qualifiers+='static' qualifiers+='definition'? | qualifiers+='definition' qualifiers+='static'?)?
	//	'property' name=UnrestrictedName ('#' referredOpposite=[pivot::Property|UnrestrictedName])? (':'
	//	ownedType=TypedMultiplicityRefCS)? ('=' default=SINGLE_QUOTED_STRING)? ('{' ((qualifiers+='composes' | qualifiers+=
	//	'!composes' | qualifiers+='derived' | qualifiers+='!derived' | qualifiers+='ordered' | qualifiers+='!ordered' |
	//	qualifiers+='readonly' | qualifiers+='!readonly' | qualifiers+='resolve' | qualifiers+='!resolve' | qualifiers+=
	//	'transient' | qualifiers+='!transient' | qualifiers+='unique' | qualifiers+='!unique' | qualifiers+='unsettable' |
	//	qualifiers+='!unsettable' | qualifiers+='volatile' | qualifiers+='!volatile') ','?)+
	//	'}')? ('{' (ownedAnnotations+=AnnotationElementCS
	//	| 'key' referredKeys+=[pivot::Property|UnrestrictedName] (',' referredKeys+=[pivot::Property|UnrestrictedName])* ';'
	//	| 'initial' UnrestrictedName? ':' ownedDefaultExpressions+=SpecificationCS? ';' | 'derivation' UnrestrictedName? ':'
	//	ownedDefaultExpressions+=SpecificationCS? ';' | ownedImplicitOpposites+=ImplicitOppositeCS ';')* '}' | ';');
	public OCLinEcoreGrammarAccess.ReferenceCSElements getReferenceCSAccess() {
		return gaOCLinEcore.getReferenceCSAccess();
	}

	public ParserRule getReferenceCSRule() {
		return getReferenceCSAccess().getRule();
	}

	//SpecificationCS essentialocl::ExpSpecificationCS:
	//	ownedExpression=ExpCS | exprString=UNQUOTED_STRING;
	public OCLinEcoreGrammarAccess.SpecificationCSElements getSpecificationCSAccess() {
		return gaOCLinEcore.getSpecificationCSAccess();
	}

	public ParserRule getSpecificationCSRule() {
		return getSpecificationCSAccess().getRule();
	}

	//StructuredClassCS base::StructuredClassCS:
	//	isAbstract?='abstract'?
	//	'class' name=UnrestrictedName
	//	ownedSignature=TemplateSignatureCS? ('extends' ownedSuperTypes+=TypedRefCS (',' ownedSuperTypes+=TypedRefCS)*)? (':'
	//	instanceClassName=SINGLE_QUOTED_STRING)? ('{' isInterface?='interface'?
	//	'}')? ('{' (ownedAnnotations+=AnnotationElementCS
	//	| ownedOperations+=OperationCS
	//	| ownedProperties+=StructuralFeatureCS
	//	| ownedConstraints+=InvariantConstraintCS)* '}' | ';');
	public OCLinEcoreGrammarAccess.StructuredClassCSElements getStructuredClassCSAccess() {
		return gaOCLinEcore.getStructuredClassCSAccess();
	}

	public ParserRule getStructuredClassCSRule() {
		return getStructuredClassCSAccess().getRule();
	}

	//StructuralFeatureCS base::StructuralFeatureCS:
	//	AttributeCS | ReferenceCS;
	public OCLinEcoreGrammarAccess.StructuralFeatureCSElements getStructuralFeatureCSAccess() {
		return gaOCLinEcore.getStructuralFeatureCSAccess();
	}

	public ParserRule getStructuralFeatureCSRule() {
		return getStructuralFeatureCSAccess().getRule();
	}

	//SysMLCS:
	//	{SysMLCS} 'sysml' (ownedDetails+=DetailCS ';' | '{' (ownedDetails+=DetailCS ';')* '}');
	public OCLinEcoreGrammarAccess.SysMLCSElements getSysMLCSAccess() {
		return gaOCLinEcore.getSysMLCSAccess();
	}

	public ParserRule getSysMLCSRule() {
		return getSysMLCSAccess().getRule();
	}

	//TypeIdentifier:
	//	UnrestrictedName
	//	| PrimitiveTypeIdentifier;
	public OCLinEcoreGrammarAccess.TypeIdentifierElements getTypeIdentifierAccess() {
		return gaOCLinEcore.getTypeIdentifierAccess();
	}

	public ParserRule getTypeIdentifierRule() {
		return getTypeIdentifierAccess().getRule();
	}

	//TypedMultiplicityRefCS base::TypedRefCS:
	//	TypedRefCS ownedMultiplicity=MultiplicityCS?;
	public OCLinEcoreGrammarAccess.TypedMultiplicityRefCSElements getTypedMultiplicityRefCSAccess() {
		return gaOCLinEcore.getTypedMultiplicityRefCSAccess();
	}

	public ParserRule getTypedMultiplicityRefCSRule() {
		return getTypedMultiplicityRefCSAccess().getRule();
	}

	//@Override
	//TemplateSignatureCS base::TemplateSignatureCS:
	//	'(' ownedParameters+=TypeParameterCS (',' ownedParameters+=TypeParameterCS)* ')' | '<'
	//	ownedParameters+=TypeParameterCS (',' ownedParameters+=TypeParameterCS)* '>';
	public OCLinEcoreGrammarAccess.TemplateSignatureCSElements getTemplateSignatureCSAccess() {
		return gaOCLinEcore.getTemplateSignatureCSAccess();
	}

	public ParserRule getTemplateSignatureCSRule() {
		return getTemplateSignatureCSAccess().getRule();
	}

	//@Override
	//TypedRefCS base::TypedRefCS:
	//	TypeLiteralCS | TypedTypeRefCS;
	public OCLinEcoreGrammarAccess.TypedRefCSElements getTypedRefCSAccess() {
		return gaOCLinEcore.getTypedRefCSAccess();
	}

	public ParserRule getTypedRefCSRule() {
		return getTypedRefCSAccess().getRule();
	}

	//@Override
	//TypedTypeRefCS base::TypedTypeRefCS:
	//	ownedPathName=PathNameCS ('(' ownedBinding=TemplateBindingCS ')' | '<' ownedBinding=TemplateBindingCS '>')?;
	public OCLinEcoreGrammarAccess.TypedTypeRefCSElements getTypedTypeRefCSAccess() {
		return gaOCLinEcore.getTypedTypeRefCSAccess();
	}

	public ParserRule getTypedTypeRefCSRule() {
		return getTypedTypeRefCSAccess().getRule();
	}

	//@Override
	//UnrestrictedName:
	//	EnumerationLiteralName
	//	| 'annotation'
	//	| 'documentation'
	//	| 'invariant'
	//	| 'literal'
	//	| 'opposite'
	//	| 'serializable'
	//	| 'sysml';
	public OCLinEcoreGrammarAccess.UnrestrictedNameElements getUnrestrictedNameAccess() {
		return gaOCLinEcore.getUnrestrictedNameAccess();
	}

	public ParserRule getUnrestrictedNameRule() {
		return getUnrestrictedNameAccess().getRule();
	}

	////generate essentialOCLCST "http://www.eclipse.org/ocl/3.0.0/EssentialOCLCST"
	//Model ContextCS:
	//	ownedExpression=ExpCS;
	public EssentialOCLGrammarAccess.ModelElements getModelAccess() {
		return gaEssentialOCL.getModelAccess();
	}

	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}

	///** <<<This is a join point for derived grammars - replace with a more disciplined grammar extensibility>>> */
	//EssentialOCLReservedKeyword:
	//	'and'
	//	| 'and2'
	//	| 'else'
	//	| 'endif'
	//	| 'if'
	//	| 'implies'
	//	| 'implies2'
	//	| 'in'
	//	| 'let'
	//	| 'not'
	//	| 'not2'
	//	| 'or'
	//	| 'or2'
	//	| 'then'
	//	| 'xor'
	//	| 'xor2';
	public EssentialOCLGrammarAccess.EssentialOCLReservedKeywordElements getEssentialOCLReservedKeywordAccess() {
		return gaEssentialOCL.getEssentialOCLReservedKeywordAccess();
	}

	public ParserRule getEssentialOCLReservedKeywordRule() {
		return getEssentialOCLReservedKeywordAccess().getRule();
	}

	///** <<<This is a join point for derived grammars - replace with a more disciplined grammar extensibility>>> */
	//EssentialOCLUnaryOperatorName:
	//	'-' | 'not' | 'not2';
	public EssentialOCLGrammarAccess.EssentialOCLUnaryOperatorNameElements getEssentialOCLUnaryOperatorNameAccess() {
		return gaEssentialOCL.getEssentialOCLUnaryOperatorNameAccess();
	}

	public ParserRule getEssentialOCLUnaryOperatorNameRule() {
		return getEssentialOCLUnaryOperatorNameAccess().getRule();
	}

	///** <<<This is a join point for derived grammars - replace with a more disciplined grammar extensibility>>> */
	//EssentialOCLInfixOperatorName:
	//	'*' | '/' | '+' | '-' | '>' | '<' | '>=' | '<=' | '=' | '<>' | 'and' | 'and2' | 'implies' | 'implies2' | 'or' |
	//	'or2' | 'xor' | 'xor2';
	public EssentialOCLGrammarAccess.EssentialOCLInfixOperatorNameElements getEssentialOCLInfixOperatorNameAccess() {
		return gaEssentialOCL.getEssentialOCLInfixOperatorNameAccess();
	}

	public ParserRule getEssentialOCLInfixOperatorNameRule() {
		return getEssentialOCLInfixOperatorNameAccess().getRule();
	}

	///** <<<This is a join point for derived grammars - replace with a more disciplined grammar extensibility>>> */
	//EssentialOCLNavigationOperatorName:
	//	'.' | '->' | '?.' | '?->';
	public EssentialOCLGrammarAccess.EssentialOCLNavigationOperatorNameElements getEssentialOCLNavigationOperatorNameAccess() {
		return gaEssentialOCL.getEssentialOCLNavigationOperatorNameAccess();
	}

	public ParserRule getEssentialOCLNavigationOperatorNameRule() {
		return getEssentialOCLNavigationOperatorNameAccess().getRule();
	}

	//BinaryOperatorName:
	//	InfixOperatorName | NavigationOperatorName;
	public EssentialOCLGrammarAccess.BinaryOperatorNameElements getBinaryOperatorNameAccess() {
		return gaEssentialOCL.getBinaryOperatorNameAccess();
	}

	public ParserRule getBinaryOperatorNameRule() {
		return getBinaryOperatorNameAccess().getRule();
	}

	//InfixOperatorName:
	//	EssentialOCLInfixOperatorName;
	public EssentialOCLGrammarAccess.InfixOperatorNameElements getInfixOperatorNameAccess() {
		return gaEssentialOCL.getInfixOperatorNameAccess();
	}

	public ParserRule getInfixOperatorNameRule() {
		return getInfixOperatorNameAccess().getRule();
	}

	//NavigationOperatorName:
	//	EssentialOCLNavigationOperatorName;
	public EssentialOCLGrammarAccess.NavigationOperatorNameElements getNavigationOperatorNameAccess() {
		return gaEssentialOCL.getNavigationOperatorNameAccess();
	}

	public ParserRule getNavigationOperatorNameRule() {
		return getNavigationOperatorNameAccess().getRule();
	}

	//UnaryOperatorName:
	//	EssentialOCLUnaryOperatorName;
	public EssentialOCLGrammarAccess.UnaryOperatorNameElements getUnaryOperatorNameAccess() {
		return gaEssentialOCL.getUnaryOperatorNameAccess();
	}

	public ParserRule getUnaryOperatorNameRule() {
		return getUnaryOperatorNameAccess().getRule();
	}

	////---------------------------------------------------------------------
	////  Names
	////---------------------------------------------------------------------
	///** <<<This is a join point for derived grammars - replace with a more disciplined grammar extensibility>>> */
	//EssentialOCLUnrestrictedName:
	//	Identifier;
	public EssentialOCLGrammarAccess.EssentialOCLUnrestrictedNameElements getEssentialOCLUnrestrictedNameAccess() {
		return gaEssentialOCL.getEssentialOCLUnrestrictedNameAccess();
	}

	public ParserRule getEssentialOCLUnrestrictedNameRule() {
		return getEssentialOCLUnrestrictedNameAccess().getRule();
	}

	///** <<<This is a join point for derived grammars - replace with a more disciplined grammar extensibility>>> */
	//EssentialOCLUnreservedName:
	//	super::UnrestrictedName
	//	| CollectionTypeIdentifier
	//	| PrimitiveTypeIdentifier
	//	| 'Map'
	//	| 'Tuple';
	public EssentialOCLGrammarAccess.EssentialOCLUnreservedNameElements getEssentialOCLUnreservedNameAccess() {
		return gaEssentialOCL.getEssentialOCLUnreservedNameAccess();
	}

	public ParserRule getEssentialOCLUnreservedNameRule() {
		return getEssentialOCLUnreservedNameAccess().getRule();
	}

	//@Override
	//UnreservedName:
	//	EssentialOCLUnreservedName;
	public EssentialOCLGrammarAccess.UnreservedNameElements getUnreservedNameAccess() {
		return gaEssentialOCL.getUnreservedNameAccess();
	}

	public ParserRule getUnreservedNameRule() {
		return getUnreservedNameAccess().getRule();
	}

	//URIPathNameCS base::PathNameCS:
	//	ownedPathElements+=URIFirstPathElementCS ('::' ownedPathElements+=NextPathElementCS)*;
	public EssentialOCLGrammarAccess.URIPathNameCSElements getURIPathNameCSAccess() {
		return gaEssentialOCL.getURIPathNameCSAccess();
	}

	public ParserRule getURIPathNameCSRule() {
		return getURIPathNameCSAccess().getRule();
	}

	//URIFirstPathElementCS base::PathElementCS:
	//	referredElement=[pivot::NamedElement|super::UnrestrictedName] | {base::PathElementWithURICS}
	//	referredElement=[pivot::Namespace|URI];
	public EssentialOCLGrammarAccess.URIFirstPathElementCSElements getURIFirstPathElementCSAccess() {
		return gaEssentialOCL.getURIFirstPathElementCSAccess();
	}

	public ParserRule getURIFirstPathElementCSRule() {
		return getURIFirstPathElementCSAccess().getRule();
	}

	//SimplePathNameCS base::PathNameCS:
	//	ownedPathElements+=FirstPathElementCS;
	public EssentialOCLGrammarAccess.SimplePathNameCSElements getSimplePathNameCSAccess() {
		return gaEssentialOCL.getSimplePathNameCSAccess();
	}

	public ParserRule getSimplePathNameCSRule() {
		return getSimplePathNameCSAccess().getRule();
	}

	////---------------------------------------------------------------------
	////  Types
	////---------------------------------------------------------------------
	//PrimitiveTypeIdentifier:
	//	'Boolean'
	//	| 'Integer'
	//	| 'Real'
	//	| 'String'
	//	| 'UnlimitedNatural'
	//	| 'OclAny'
	//	| 'OclInvalid'
	//	| 'OclVoid';
	public EssentialOCLGrammarAccess.PrimitiveTypeIdentifierElements getPrimitiveTypeIdentifierAccess() {
		return gaEssentialOCL.getPrimitiveTypeIdentifierAccess();
	}

	public ParserRule getPrimitiveTypeIdentifierRule() {
		return getPrimitiveTypeIdentifierAccess().getRule();
	}

	//PrimitiveTypeCS base::PrimitiveTypeRefCS:
	//	name=PrimitiveTypeIdentifier;
	public EssentialOCLGrammarAccess.PrimitiveTypeCSElements getPrimitiveTypeCSAccess() {
		return gaEssentialOCL.getPrimitiveTypeCSAccess();
	}

	public ParserRule getPrimitiveTypeCSRule() {
		return getPrimitiveTypeCSAccess().getRule();
	}

	//CollectionTypeIdentifier:
	//	'Set'
	//	| 'Bag'
	//	| 'Sequence'
	//	| 'Collection'
	//	| 'OrderedSet';
	public EssentialOCLGrammarAccess.CollectionTypeIdentifierElements getCollectionTypeIdentifierAccess() {
		return gaEssentialOCL.getCollectionTypeIdentifierAccess();
	}

	public ParserRule getCollectionTypeIdentifierRule() {
		return getCollectionTypeIdentifierAccess().getRule();
	}

	//CollectionTypeCS:
	//	name=CollectionTypeIdentifier ('(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS
	//	? ')')?;
	public EssentialOCLGrammarAccess.CollectionTypeCSElements getCollectionTypeCSAccess() {
		return gaEssentialOCL.getCollectionTypeCSAccess();
	}

	public ParserRule getCollectionTypeCSRule() {
		return getCollectionTypeCSAccess().getRule();
	}

	//MapTypeCS:
	//	name='Map' ('(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')')?;
	public EssentialOCLGrammarAccess.MapTypeCSElements getMapTypeCSAccess() {
		return gaEssentialOCL.getMapTypeCSAccess();
	}

	public ParserRule getMapTypeCSRule() {
		return getMapTypeCSAccess().getRule();
	}

	//TupleTypeCS base::TupleTypeCS:
	//	name='Tuple' ('(' (ownedParts+=TuplePartCS (',' ownedParts+=TuplePartCS)*)? ')')?;
	public EssentialOCLGrammarAccess.TupleTypeCSElements getTupleTypeCSAccess() {
		return gaEssentialOCL.getTupleTypeCSAccess();
	}

	public ParserRule getTupleTypeCSRule() {
		return getTupleTypeCSAccess().getRule();
	}

	//TuplePartCS base::TuplePartCS:
	//	name=super::UnrestrictedName ':' ownedType=TypeExpCS;
	public EssentialOCLGrammarAccess.TuplePartCSElements getTuplePartCSAccess() {
		return gaEssentialOCL.getTuplePartCSAccess();
	}

	public ParserRule getTuplePartCSRule() {
		return getTuplePartCSAccess().getRule();
	}

	////---------------------------------------------------------------------
	////  Literals
	////---------------------------------------------------------------------
	//CollectionLiteralExpCS:
	//	ownedType=CollectionTypeCS
	//	'{' (ownedParts+=CollectionLiteralPartCS (',' ownedParts+=CollectionLiteralPartCS)*)?
	//	'}';
	public EssentialOCLGrammarAccess.CollectionLiteralExpCSElements getCollectionLiteralExpCSAccess() {
		return gaEssentialOCL.getCollectionLiteralExpCSAccess();
	}

	public ParserRule getCollectionLiteralExpCSRule() {
		return getCollectionLiteralExpCSAccess().getRule();
	}

	//CollectionLiteralPartCS:
	//	ownedExpression=ExpCS ('..' ownedLastExpression=ExpCS)? | ownedExpression=PatternExpCS;
	public EssentialOCLGrammarAccess.CollectionLiteralPartCSElements getCollectionLiteralPartCSAccess() {
		return gaEssentialOCL.getCollectionLiteralPartCSAccess();
	}

	public ParserRule getCollectionLiteralPartCSRule() {
		return getCollectionLiteralPartCSAccess().getRule();
	}

	//CollectionPatternCS:
	//	ownedType=CollectionTypeCS
	//	'{' (ownedParts+=PatternExpCS (',' ownedParts+=PatternExpCS)* ('++' restVariableName=Identifier))?
	//	'}';
	public EssentialOCLGrammarAccess.CollectionPatternCSElements getCollectionPatternCSAccess() {
		return gaEssentialOCL.getCollectionPatternCSAccess();
	}

	public ParserRule getCollectionPatternCSRule() {
		return getCollectionPatternCSAccess().getRule();
	}

	//ShadowPartCS:
	//	referredProperty=[pivot::Property|super::UnrestrictedName] '=' ownedInitExpression=(ExpCS | PatternExpCS) |
	//	ownedInitExpression=StringLiteralExpCS;
	public EssentialOCLGrammarAccess.ShadowPartCSElements getShadowPartCSAccess() {
		return gaEssentialOCL.getShadowPartCSAccess();
	}

	public ParserRule getShadowPartCSRule() {
		return getShadowPartCSAccess().getRule();
	}

	//PatternExpCS:
	//	patternVariableName=super::UnrestrictedName? ':' ownedPatternType=TypeExpCS;
	public EssentialOCLGrammarAccess.PatternExpCSElements getPatternExpCSAccess() {
		return gaEssentialOCL.getPatternExpCSAccess();
	}

	public ParserRule getPatternExpCSRule() {
		return getPatternExpCSAccess().getRule();
	}

	//LambdaLiteralExpCS:
	//	'Lambda' '{' ownedExpressionCS=ExpCS '}';
	public EssentialOCLGrammarAccess.LambdaLiteralExpCSElements getLambdaLiteralExpCSAccess() {
		return gaEssentialOCL.getLambdaLiteralExpCSAccess();
	}

	public ParserRule getLambdaLiteralExpCSRule() {
		return getLambdaLiteralExpCSAccess().getRule();
	}

	//MapLiteralExpCS:
	//	ownedType=MapTypeCS '{' (ownedParts+=MapLiteralPartCS (',' ownedParts+=MapLiteralPartCS)*)? '}';
	public EssentialOCLGrammarAccess.MapLiteralExpCSElements getMapLiteralExpCSAccess() {
		return gaEssentialOCL.getMapLiteralExpCSAccess();
	}

	public ParserRule getMapLiteralExpCSRule() {
		return getMapLiteralExpCSAccess().getRule();
	}

	//MapLiteralPartCS:
	//	ownedKey=ExpCS '<-' ownedValue=ExpCS;
	public EssentialOCLGrammarAccess.MapLiteralPartCSElements getMapLiteralPartCSAccess() {
		return gaEssentialOCL.getMapLiteralPartCSAccess();
	}

	public ParserRule getMapLiteralPartCSRule() {
		return getMapLiteralPartCSAccess().getRule();
	}

	//PrimitiveLiteralExpCS:
	//	NumberLiteralExpCS
	//	| StringLiteralExpCS
	//	| BooleanLiteralExpCS
	//	| UnlimitedNaturalLiteralExpCS
	//	| InvalidLiteralExpCS
	//	| NullLiteralExpCS;
	public EssentialOCLGrammarAccess.PrimitiveLiteralExpCSElements getPrimitiveLiteralExpCSAccess() {
		return gaEssentialOCL.getPrimitiveLiteralExpCSAccess();
	}

	public ParserRule getPrimitiveLiteralExpCSRule() {
		return getPrimitiveLiteralExpCSAccess().getRule();
	}

	//TupleLiteralExpCS:
	//	'Tuple' '{' ownedParts+=TupleLiteralPartCS (',' ownedParts+=TupleLiteralPartCS)* '}';
	public EssentialOCLGrammarAccess.TupleLiteralExpCSElements getTupleLiteralExpCSAccess() {
		return gaEssentialOCL.getTupleLiteralExpCSAccess();
	}

	public ParserRule getTupleLiteralExpCSRule() {
		return getTupleLiteralExpCSAccess().getRule();
	}

	//TupleLiteralPartCS:
	//	name=super::UnrestrictedName (':' ownedType=TypeExpCS)? '=' ownedInitExpression=ExpCS;
	public EssentialOCLGrammarAccess.TupleLiteralPartCSElements getTupleLiteralPartCSAccess() {
		return gaEssentialOCL.getTupleLiteralPartCSAccess();
	}

	public ParserRule getTupleLiteralPartCSRule() {
		return getTupleLiteralPartCSAccess().getRule();
	}

	//NumberLiteralExpCS:
	//	symbol=NUMBER_LITERAL;
	public EssentialOCLGrammarAccess.NumberLiteralExpCSElements getNumberLiteralExpCSAccess() {
		return gaEssentialOCL.getNumberLiteralExpCSAccess();
	}

	public ParserRule getNumberLiteralExpCSRule() {
		return getNumberLiteralExpCSAccess().getRule();
	}

	//StringLiteralExpCS:
	//	segments+=StringLiteral+;
	public EssentialOCLGrammarAccess.StringLiteralExpCSElements getStringLiteralExpCSAccess() {
		return gaEssentialOCL.getStringLiteralExpCSAccess();
	}

	public ParserRule getStringLiteralExpCSRule() {
		return getStringLiteralExpCSAccess().getRule();
	}

	//BooleanLiteralExpCS:
	//	symbol='true'
	//	| symbol='false';
	public EssentialOCLGrammarAccess.BooleanLiteralExpCSElements getBooleanLiteralExpCSAccess() {
		return gaEssentialOCL.getBooleanLiteralExpCSAccess();
	}

	public ParserRule getBooleanLiteralExpCSRule() {
		return getBooleanLiteralExpCSAccess().getRule();
	}

	//UnlimitedNaturalLiteralExpCS:
	//	{UnlimitedNaturalLiteralExpCS} '*';
	public EssentialOCLGrammarAccess.UnlimitedNaturalLiteralExpCSElements getUnlimitedNaturalLiteralExpCSAccess() {
		return gaEssentialOCL.getUnlimitedNaturalLiteralExpCSAccess();
	}

	public ParserRule getUnlimitedNaturalLiteralExpCSRule() {
		return getUnlimitedNaturalLiteralExpCSAccess().getRule();
	}

	//InvalidLiteralExpCS:
	//	{InvalidLiteralExpCS} 'invalid';
	public EssentialOCLGrammarAccess.InvalidLiteralExpCSElements getInvalidLiteralExpCSAccess() {
		return gaEssentialOCL.getInvalidLiteralExpCSAccess();
	}

	public ParserRule getInvalidLiteralExpCSRule() {
		return getInvalidLiteralExpCSAccess().getRule();
	}

	//NullLiteralExpCS:
	//	{NullLiteralExpCS} 'null';
	public EssentialOCLGrammarAccess.NullLiteralExpCSElements getNullLiteralExpCSAccess() {
		return gaEssentialOCL.getNullLiteralExpCSAccess();
	}

	public ParserRule getNullLiteralExpCSRule() {
		return getNullLiteralExpCSAccess().getRule();
	}

	//TypeLiteralCS base::TypedRefCS:
	//	PrimitiveTypeCS
	//	| CollectionTypeCS
	//	| MapTypeCS
	//	| TupleTypeCS;
	public EssentialOCLGrammarAccess.TypeLiteralCSElements getTypeLiteralCSAccess() {
		return gaEssentialOCL.getTypeLiteralCSAccess();
	}

	public ParserRule getTypeLiteralCSRule() {
		return getTypeLiteralCSAccess().getRule();
	}

	//TypeLiteralWithMultiplicityCS base::TypedRefCS:
	//	TypeLiteralCS ownedMultiplicity=MultiplicityCS?;
	public EssentialOCLGrammarAccess.TypeLiteralWithMultiplicityCSElements getTypeLiteralWithMultiplicityCSAccess() {
		return gaEssentialOCL.getTypeLiteralWithMultiplicityCSAccess();
	}

	public ParserRule getTypeLiteralWithMultiplicityCSRule() {
		return getTypeLiteralWithMultiplicityCSAccess().getRule();
	}

	//TypeLiteralExpCS:
	//	ownedType=TypeLiteralWithMultiplicityCS;
	public EssentialOCLGrammarAccess.TypeLiteralExpCSElements getTypeLiteralExpCSAccess() {
		return gaEssentialOCL.getTypeLiteralExpCSAccess();
	}

	public ParserRule getTypeLiteralExpCSRule() {
		return getTypeLiteralExpCSAccess().getRule();
	}

	//TypeNameExpCS:
	//	ownedPathName=PathNameCS (ownedCurlyBracketedClause=CurlyBracketedClauseCS ('{' ownedPatternGuard=ExpCS '}')?)?;
	public EssentialOCLGrammarAccess.TypeNameExpCSElements getTypeNameExpCSAccess() {
		return gaEssentialOCL.getTypeNameExpCSAccess();
	}

	public ParserRule getTypeNameExpCSRule() {
		return getTypeNameExpCSAccess().getRule();
	}

	//TypeExpWithoutMultiplicityCS base::TypedRefCS:
	//	TypeNameExpCS | TypeLiteralCS | CollectionPatternCS;
	public EssentialOCLGrammarAccess.TypeExpWithoutMultiplicityCSElements getTypeExpWithoutMultiplicityCSAccess() {
		return gaEssentialOCL.getTypeExpWithoutMultiplicityCSAccess();
	}

	public ParserRule getTypeExpWithoutMultiplicityCSRule() {
		return getTypeExpWithoutMultiplicityCSAccess().getRule();
	}

	//TypeExpCS base::TypedRefCS:
	//	TypeExpWithoutMultiplicityCS ownedMultiplicity=MultiplicityCS?;
	public EssentialOCLGrammarAccess.TypeExpCSElements getTypeExpCSAccess() {
		return gaEssentialOCL.getTypeExpCSAccess();
	}

	public ParserRule getTypeExpCSRule() {
		return getTypeExpCSAccess().getRule();
	}

	////---------------------------------------------------------------------
	////  Expressions
	////---------------------------------------------------------------------
	//// An ExpCS permits a LetExpCS only in the final term to ensure
	////  that let is right associative, whereas infix operators are left associative.
	////   a = 64 / 16 / let b : Integer in 8 / let c : Integer in 4
	//// is
	////   a = (64 / 16) / (let b : Integer in 8 / (let c : Integer in 4 ))
	///* An expression elaborates a prefixed expression with zero or more binary operator and expression suffixes.
	// * An optionally prefixed let expression is permitted except when suffixed with further expressions.*/
	//ExpCS:
	//	PrefixedPrimaryExpCS ({InfixExpCS.ownedLeft=current} name=BinaryOperatorName ownedRight=ExpCS)? | PrefixedLetExpCS;
	public EssentialOCLGrammarAccess.ExpCSElements getExpCSAccess() {
		return gaEssentialOCL.getExpCSAccess();
	}

	public ParserRule getExpCSRule() {
		return getExpCSAccess().getRule();
	}

	///* A prefixed let expression elaborates a let expression with zero or more unary prefix operators. */
	//PrefixedLetExpCS ExpCS:
	//	{PrefixExpCS} name=UnaryOperatorName ownedRight=PrefixedLetExpCS | LetExpCS;
	public EssentialOCLGrammarAccess.PrefixedLetExpCSElements getPrefixedLetExpCSAccess() {
		return gaEssentialOCL.getPrefixedLetExpCSAccess();
	}

	public ParserRule getPrefixedLetExpCSRule() {
		return getPrefixedLetExpCSAccess().getRule();
	}

	///* A prefixed primary expression elaborates a primary expression with zero or more unary prefix operators. */
	//PrefixedPrimaryExpCS ExpCS:
	//	{PrefixExpCS} name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS | PrimaryExpCS;
	public EssentialOCLGrammarAccess.PrefixedPrimaryExpCSElements getPrefixedPrimaryExpCSAccess() {
		return gaEssentialOCL.getPrefixedPrimaryExpCSAccess();
	}

	public ParserRule getPrefixedPrimaryExpCSRule() {
		return getPrefixedPrimaryExpCSAccess().getRule();
	}

	///* A primary expression identifies the basic expressions from which more complex expressions may be constructed. */
	//PrimaryExpCS ExpCS:
	//	NestedExpCS
	//	| IfExpCS
	//	| SelfExpCS
	//	| PrimitiveLiteralExpCS
	//	| TupleLiteralExpCS
	//	| MapLiteralExpCS
	//	| CollectionLiteralExpCS
	//	| LambdaLiteralExpCS
	//	| TypeLiteralExpCS
	//	| NameExpCS;
	public EssentialOCLGrammarAccess.PrimaryExpCSElements getPrimaryExpCSAccess() {
		return gaEssentialOCL.getPrimaryExpCSAccess();
	}

	public ParserRule getPrimaryExpCSRule() {
		return getPrimaryExpCSAccess().getRule();
	}

	///* A name expression is a generalised rule for expressions that start with a name and which may be followed by square, round or
	// * curly bracket clauses and optionally an @pre as well.*/
	//NameExpCS:
	//	ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS*
	//	ownedRoundBracketedClause=RoundBracketedClauseCS? ownedCurlyBracketedClause=CurlyBracketedClauseCS? (isPre?='@'
	//	'pre')?;
	public EssentialOCLGrammarAccess.NameExpCSElements getNameExpCSAccess() {
		return gaEssentialOCL.getNameExpCSAccess();
	}

	public ParserRule getNameExpCSRule() {
		return getNameExpCSAccess().getRule();
	}

	///* A curly bracket clause is a generalized rule for the literal arguments of collections, maps, tuples and shadows.*/
	//CurlyBracketedClauseCS:
	//	{CurlyBracketedClauseCS} '{' (ownedParts+=ShadowPartCS (',' ownedParts+=ShadowPartCS)*)? '}';
	public EssentialOCLGrammarAccess.CurlyBracketedClauseCSElements getCurlyBracketedClauseCSAccess() {
		return gaEssentialOCL.getCurlyBracketedClauseCSAccess();
	}

	public ParserRule getCurlyBracketedClauseCSRule() {
		return getCurlyBracketedClauseCSAccess().getRule();
	}

	///* A curly bracket clause is a generalized rule for template specialisations and operations arguments.*/
	//RoundBracketedClauseCS:
	//	{RoundBracketedClauseCS} '(' (ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS |
	//	NavigatingSemiArgCS | NavigatingBarArgCS)*)? ')';
	public EssentialOCLGrammarAccess.RoundBracketedClauseCSElements getRoundBracketedClauseCSAccess() {
		return gaEssentialOCL.getRoundBracketedClauseCSAccess();
	}

	public ParserRule getRoundBracketedClauseCSRule() {
		return getRoundBracketedClauseCSAccess().getRule();
	}

	///* A square bracket clause is a generalized rule for association class qualifiers and roles.*/
	//SquareBracketedClauseCS:
	//	'[' ownedTerms+=ExpCS (',' ownedTerms+=ExpCS)* ']';
	public EssentialOCLGrammarAccess.SquareBracketedClauseCSElements getSquareBracketedClauseCSAccess() {
		return gaEssentialOCL.getSquareBracketedClauseCSAccess();
	}

	public ParserRule getSquareBracketedClauseCSRule() {
		return getSquareBracketedClauseCSAccess().getRule();
	}

	///* A navigating argument is a generalized rule for the first argument in a round bracket clause. This is typically the first operation
	// * parameter or an iterator. */
	//NavigatingArgCS:
	//	ownedNameExpression=NavigatingArgExpCS ('<-' ownedCoIterator=CoIteratorVariableCS ('=' ownedInitExpression=ExpCS)? |
	//	':' ownedType=TypeExpCS ('<-' ownedCoIterator=CoIteratorVariableCS)? ('=' ownedInitExpression=ExpCS)? | (':'
	//	ownedType=TypeExpCS)? ('<-' ownedCoIterator=CoIteratorVariableCS)? 'in' ownedInitExpression=ExpCS)?
	//	| ':' ownedType=TypeExpCS;
	public EssentialOCLGrammarAccess.NavigatingArgCSElements getNavigatingArgCSAccess() {
		return gaEssentialOCL.getNavigatingArgCSAccess();
	}

	public ParserRule getNavigatingArgCSRule() {
		return getNavigatingArgCSAccess().getRule();
	}

	//// Type-less init is an illegal infix expression
	//
	///* A navigating bar argument is a generalized rule for a bar-prefixed argument in a round bracket clause. This is typically the body of an iteration. */
	//NavigatingBarArgCS NavigatingArgCS:
	//	prefix='|' ownedNameExpression=NavigatingArgExpCS (':' ownedType=TypeExpCS ('=' ownedInitExpression=ExpCS)?)?;
	public EssentialOCLGrammarAccess.NavigatingBarArgCSElements getNavigatingBarArgCSAccess() {
		return gaEssentialOCL.getNavigatingBarArgCSAccess();
	}

	public ParserRule getNavigatingBarArgCSRule() {
		return getNavigatingBarArgCSAccess().getRule();
	}

	//// Type-less init is an illegal infix expression
	//
	///* A navigating comma argument is a generalized rule for non-first argument in a round bracket clause. These are typically non-first operation
	// * parameters or a second iterator. */
	//NavigatingCommaArgCS NavigatingArgCS:
	//	prefix=',' ownedNameExpression=NavigatingArgExpCS ('<-' ownedCoIterator=CoIteratorVariableCS ('='
	//	ownedInitExpression=ExpCS)? | ':' ownedType=TypeExpCS ('<-' ownedCoIterator=CoIteratorVariableCS)? ('='
	//	ownedInitExpression=ExpCS)? | (':' ownedType=TypeExpCS)? ('<-' ownedCoIterator=CoIteratorVariableCS)? 'in'
	//	ownedInitExpression=ExpCS)?;
	public EssentialOCLGrammarAccess.NavigatingCommaArgCSElements getNavigatingCommaArgCSAccess() {
		return gaEssentialOCL.getNavigatingCommaArgCSAccess();
	}

	public ParserRule getNavigatingCommaArgCSRule() {
		return getNavigatingCommaArgCSAccess().getRule();
	}

	//// Type-less init is an illegal infix expression
	//
	///* A navigating semi argument is a generalized rule for a semicolon prefixed argument in a round bracket clause. This is typically an iterate accumulator. */
	//NavigatingSemiArgCS NavigatingArgCS:
	//	prefix=';' ownedNameExpression=NavigatingArgExpCS (':' ownedType=TypeExpCS ('=' ownedInitExpression=ExpCS)?)?;
	public EssentialOCLGrammarAccess.NavigatingSemiArgCSElements getNavigatingSemiArgCSAccess() {
		return gaEssentialOCL.getNavigatingSemiArgCSAccess();
	}

	public ParserRule getNavigatingSemiArgCSRule() {
		return getNavigatingSemiArgCSAccess().getRule();
	}

	//// Type-less init is an illegal infix expression
	//
	//NavigatingArgExpCS ExpCS:
	//	ExpCS// '?'	-- defined by Complete OCL
	//;
	public EssentialOCLGrammarAccess.NavigatingArgExpCSElements getNavigatingArgExpCSAccess() {
		return gaEssentialOCL.getNavigatingArgExpCSAccess();
	}

	public ParserRule getNavigatingArgExpCSRule() {
		return getNavigatingArgExpCSAccess().getRule();
	}

	//CoIteratorVariableCS VariableCS:
	//	name=super::UnrestrictedName (':' ownedType=TypeExpCS)?;
	public EssentialOCLGrammarAccess.CoIteratorVariableCSElements getCoIteratorVariableCSAccess() {
		return gaEssentialOCL.getCoIteratorVariableCSAccess();
	}

	public ParserRule getCoIteratorVariableCSRule() {
		return getCoIteratorVariableCSAccess().getRule();
	}

	//IfExpCS:
	//	'if' ownedCondition=(ExpCS | PatternExpCS)
	//	'then' ownedThenExpression=ExpCS
	////	ifThenExpressions+=IfThenExpCS
	//	ownedIfThenExpressions+=ElseIfThenExpCS*
	//	'else' ownedElseExpression=ExpCS
	//	'endif';
	public EssentialOCLGrammarAccess.IfExpCSElements getIfExpCSAccess() {
		return gaEssentialOCL.getIfExpCSAccess();
	}

	public ParserRule getIfExpCSRule() {
		return getIfExpCSAccess().getRule();
	}

	////IfThenExpCS returns IfThenExpCS:
	////	'if' condition=ExpCS
	////	'then' thenExpression=ExpCS
	////;
	//ElseIfThenExpCS IfThenExpCS:
	//	'elseif' ownedCondition=ExpCS
	//	'then' ownedThenExpression=ExpCS;
	public EssentialOCLGrammarAccess.ElseIfThenExpCSElements getElseIfThenExpCSAccess() {
		return gaEssentialOCL.getElseIfThenExpCSAccess();
	}

	public ParserRule getElseIfThenExpCSRule() {
		return getElseIfThenExpCSAccess().getRule();
	}

	//LetExpCS:
	//	'let' ownedVariables+=LetVariableCS (',' ownedVariables+=LetVariableCS)*
	//	'in' ownedInExpression=ExpCS;
	public EssentialOCLGrammarAccess.LetExpCSElements getLetExpCSAccess() {
		return gaEssentialOCL.getLetExpCSAccess();
	}

	public ParserRule getLetExpCSRule() {
		return getLetExpCSAccess().getRule();
	}

	//LetVariableCS:
	//	name=super::UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS? (':' ownedType=TypeExpCS)? '='
	//	ownedInitExpression=ExpCS;
	public EssentialOCLGrammarAccess.LetVariableCSElements getLetVariableCSAccess() {
		return gaEssentialOCL.getLetVariableCSAccess();
	}

	public ParserRule getLetVariableCSRule() {
		return getLetVariableCSAccess().getRule();
	}

	//NestedExpCS:
	//	'(' ownedExpression=ExpCS ')';
	public EssentialOCLGrammarAccess.NestedExpCSElements getNestedExpCSAccess() {
		return gaEssentialOCL.getNestedExpCSAccess();
	}

	public ParserRule getNestedExpCSRule() {
		return getNestedExpCSAccess().getRule();
	}

	//SelfExpCS:
	//	{SelfExpCS} 'self';
	public EssentialOCLGrammarAccess.SelfExpCSElements getSelfExpCSAccess() {
		return gaEssentialOCL.getSelfExpCSAccess();
	}

	public ParserRule getSelfExpCSRule() {
		return getSelfExpCSAccess().getRule();
	}

	//MultiplicityBoundsCS:
	//	lowerBound=LOWER ('..' upperBound=UPPER)?;
	public BaseGrammarAccess.MultiplicityBoundsCSElements getMultiplicityBoundsCSAccess() {
		return gaBase.getMultiplicityBoundsCSAccess();
	}

	public ParserRule getMultiplicityBoundsCSRule() {
		return getMultiplicityBoundsCSAccess().getRule();
	}

	//MultiplicityCS:
	//	'[' (MultiplicityBoundsCS | MultiplicityStringCS) ('|?' | isNullFree?='|1')? ']';
	public BaseGrammarAccess.MultiplicityCSElements getMultiplicityCSAccess() {
		return gaBase.getMultiplicityCSAccess();
	}

	public ParserRule getMultiplicityCSRule() {
		return getMultiplicityCSAccess().getRule();
	}

	//MultiplicityStringCS:
	//	stringBounds=('*' | '+' | '?');
	public BaseGrammarAccess.MultiplicityStringCSElements getMultiplicityStringCSAccess() {
		return gaBase.getMultiplicityStringCSAccess();
	}

	public ParserRule getMultiplicityStringCSRule() {
		return getMultiplicityStringCSAccess().getRule();
	}

	//PathNameCS:
	//	ownedPathElements+=FirstPathElementCS ('::' ownedPathElements+=NextPathElementCS)*;
	public BaseGrammarAccess.PathNameCSElements getPathNameCSAccess() {
		return gaBase.getPathNameCSAccess();
	}

	public ParserRule getPathNameCSRule() {
		return getPathNameCSAccess().getRule();
	}

	//UnreservedPathNameCS PathNameCS:
	//	ownedPathElements+=NextPathElementCS ('::' ownedPathElements+=NextPathElementCS)*;
	public BaseGrammarAccess.UnreservedPathNameCSElements getUnreservedPathNameCSAccess() {
		return gaBase.getUnreservedPathNameCSAccess();
	}

	public ParserRule getUnreservedPathNameCSRule() {
		return getUnreservedPathNameCSAccess().getRule();
	}

	//FirstPathElementCS PathElementCS:
	//	referredElement=[pivot::NamedElement|super::UnrestrictedName];
	public BaseGrammarAccess.FirstPathElementCSElements getFirstPathElementCSAccess() {
		return gaBase.getFirstPathElementCSAccess();
	}

	public ParserRule getFirstPathElementCSRule() {
		return getFirstPathElementCSAccess().getRule();
	}

	//NextPathElementCS PathElementCS:
	//	referredElement=[pivot::NamedElement|super::UnreservedName];
	public BaseGrammarAccess.NextPathElementCSElements getNextPathElementCSAccess() {
		return gaBase.getNextPathElementCSAccess();
	}

	public ParserRule getNextPathElementCSRule() {
		return getNextPathElementCSAccess().getRule();
	}

	//TemplateBindingCS:
	//	ownedSubstitutions+=TemplateParameterSubstitutionCS (',' ownedSubstitutions+=TemplateParameterSubstitutionCS)*
	//	ownedMultiplicity=MultiplicityCS?;
	public BaseGrammarAccess.TemplateBindingCSElements getTemplateBindingCSAccess() {
		return gaBase.getTemplateBindingCSAccess();
	}

	public ParserRule getTemplateBindingCSRule() {
		return getTemplateBindingCSAccess().getRule();
	}

	//TemplateParameterSubstitutionCS:
	//	ownedActualParameter=TypeRefCS;
	public BaseGrammarAccess.TemplateParameterSubstitutionCSElements getTemplateParameterSubstitutionCSAccess() {
		return gaBase.getTemplateParameterSubstitutionCSAccess();
	}

	public ParserRule getTemplateParameterSubstitutionCSRule() {
		return getTemplateParameterSubstitutionCSAccess().getRule();
	}

	//TypeParameterCS:
	//	name=super::UnrestrictedName ('extends' ownedExtends+=super::TypedRefCS ('&&' ownedExtends+=super::TypedRefCS)*)?;
	public BaseGrammarAccess.TypeParameterCSElements getTypeParameterCSAccess() {
		return gaBase.getTypeParameterCSAccess();
	}

	public ParserRule getTypeParameterCSRule() {
		return getTypeParameterCSAccess().getRule();
	}

	//TypeRefCS:
	//	super::TypedRefCS | WildcardTypeRefCS;
	public BaseGrammarAccess.TypeRefCSElements getTypeRefCSAccess() {
		return gaBase.getTypeRefCSAccess();
	}

	public ParserRule getTypeRefCSRule() {
		return getTypeRefCSAccess().getRule();
	}

	//WildcardTypeRefCS:
	//	{WildcardTypeRefCS} '?' ('extends' ownedExtends=super::TypedRefCS)?;
	public BaseGrammarAccess.WildcardTypeRefCSElements getWildcardTypeRefCSAccess() {
		return gaBase.getWildcardTypeRefCSAccess();
	}

	public ParserRule getWildcardTypeRefCSRule() {
		return getWildcardTypeRefCSAccess().getRule();
	}

	//ID:
	//	SIMPLE_ID | ESCAPED_ID;
	public BaseGrammarAccess.IDElements getIDAccess() {
		return gaBase.getIDAccess();
	}

	public ParserRule getIDRule() {
		return getIDAccess().getRule();
	}

	//Identifier:
	//	ID;
	public BaseGrammarAccess.IdentifierElements getIdentifierAccess() {
		return gaBase.getIdentifierAccess();
	}

	public ParserRule getIdentifierRule() {
		return getIdentifierAccess().getRule();
	}

	///* A lowerbounded integer is used to define the lowerbound of a collection multiplicity. The value may not be the unlimited value. */
	//LOWER ecore::EInt:
	//	INT;
	public BaseGrammarAccess.LOWERElements getLOWERAccess() {
		return gaBase.getLOWERAccess();
	}

	public ParserRule getLOWERRule() {
		return getLOWERAccess().getRule();
	}

	///* A number may be an integer or floating point value. The declaration here appears to be that for just an integer. This is to avoid
	// * lookahead conflicts in simple lexers between a dot within a floating point number and the dot-dot in a CollectionLiteralPartCS. A
	// * practical implementation should give high priority to a successful parse of INT ('.' INT)? (('e' | 'E') ('+' | '-')? INT)? than
	// * to the unsuccessful partial parse of INT '..'. The type of the INT terminal is String to allow the floating point syntax to be used.
	// */
	//NUMBER_LITERAL BigNumber:
	//	INT;
	public BaseGrammarAccess.NUMBER_LITERALElements getNUMBER_LITERALAccess() {
		return gaBase.getNUMBER_LITERALAccess();
	}

	public ParserRule getNUMBER_LITERALRule() {
		return getNUMBER_LITERALAccess().getRule();
	}

	//// EssentialOCLTokenSource pieces this together ('.' INT)? (('e' | 'E') ('+' | '-')? INT)?;
	//
	//StringLiteral:
	//	SINGLE_QUOTED_STRING;
	public BaseGrammarAccess.StringLiteralElements getStringLiteralAccess() {
		return gaBase.getStringLiteralAccess();
	}

	public ParserRule getStringLiteralRule() {
		return getStringLiteralAccess().getRule();
	}

	///* An upperbounded integer is used to define the upperbound of a collection multiplicity. The value may be the unlimited value. */
	//UPPER ecore::EInt:
	//	INT | '*';
	public BaseGrammarAccess.UPPERElements getUPPERAccess() {
		return gaBase.getUPPERAccess();
	}

	public ParserRule getUPPERRule() {
		return getUPPERAccess().getRule();
	}

	//URI:
	//	SINGLE_QUOTED_STRING;
	public BaseGrammarAccess.URIElements getURIAccess() {
		return gaBase.getURIAccess();
	}

	public ParserRule getURIRule() {
		return getURIAccess().getRule();
	}

	//terminal fragment ESCAPED_CHARACTER:
	//	'\\' ('b' | 't' | 'n' | 'f' | 'r' | 'u' | '"' | "'" | '\\');
	public TerminalRule getESCAPED_CHARACTERRule() {
		return gaBase.getESCAPED_CHARACTERRule();
	}

	//terminal fragment LETTER_CHARACTER:
	//	'a'..'z' | 'A'..'Z' | '_';
	public TerminalRule getLETTER_CHARACTERRule() {
		return gaBase.getLETTER_CHARACTERRule();
	}

	//terminal DOUBLE_QUOTED_STRING:
	//	'"' (ESCAPED_CHARACTER | !('\\' | '"'))* '"';
	public TerminalRule getDOUBLE_QUOTED_STRINGRule() {
		return gaBase.getDOUBLE_QUOTED_STRINGRule();
	}

	//terminal SINGLE_QUOTED_STRING:
	//	"'" (ESCAPED_CHARACTER | !('\\' | "'"))* "'";
	public TerminalRule getSINGLE_QUOTED_STRINGRule() {
		return gaBase.getSINGLE_QUOTED_STRINGRule();
	}

	//terminal ML_SINGLE_QUOTED_STRING:
	//	"/'"->"'/";
	public TerminalRule getML_SINGLE_QUOTED_STRINGRule() {
		return gaBase.getML_SINGLE_QUOTED_STRINGRule();
	}

	//terminal SIMPLE_ID:
	//	LETTER_CHARACTER (LETTER_CHARACTER | '0'..'9')*;
	public TerminalRule getSIMPLE_IDRule() {
		return gaBase.getSIMPLE_IDRule();
	}

	//terminal ESCAPED_ID:
	//	"_" SINGLE_QUOTED_STRING;
	public TerminalRule getESCAPED_IDRule() {
		return gaBase.getESCAPED_IDRule();
	}

	//terminal INT:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaBase.getINTRule();
	}

	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaBase.getML_COMMENTRule();
	}

	//terminal SL_COMMENT:
	//	'--' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaBase.getSL_COMMENTRule();
	}

	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaBase.getWSRule();
	}

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaBase.getANY_OTHERRule();
	}
}
