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
package org.eclipse.sme.frsl.serializer;

import com.google.inject.Inject;
import org.eclipse.sme.frslcs.ActStepCS;
import org.eclipse.sme.frslcs.ActorActionCS;
import org.eclipse.sme.frslcs.ActorCS;
import org.eclipse.sme.frslcs.AltFlowCS;
import org.eclipse.sme.frslcs.AssociationCS;
import org.eclipse.sme.frslcs.ConstraintCS;
import org.eclipse.sme.frslcs.ExtendCS;
import org.eclipse.sme.frslcs.ExtensionPointCS;
import org.eclipse.sme.frslcs.FrslCSPackage;
import org.eclipse.sme.frslcs.FrslModelCS;
import org.eclipse.sme.frslcs.ObjVarCS;
import org.eclipse.sme.frslcs.RejoinStepCS;
import org.eclipse.sme.frslcs.SnapshotPatternCS;
import org.eclipse.sme.frslcs.SystemActionCS;
import org.eclipse.sme.frslcs.UCStepCS;
import org.eclipse.sme.frslcs.UsecaseCS;
import org.eclipse.sme.frslcs.UsecasePostconditionCS;
import org.eclipse.sme.frslcs.UsecasePreconditionCS;
import org.eclipse.sme.frslcs.VarLinkCS;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.xtext.basecs.AnnotationCS;
import org.eclipse.ocl.xtext.basecs.AttributeCS;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.DataTypeCS;
import org.eclipse.ocl.xtext.basecs.DetailCS;
import org.eclipse.ocl.xtext.basecs.DocumentationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationLiteralCS;
import org.eclipse.ocl.xtext.basecs.ImplicitOppositeCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityBoundsCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityStringCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathElementWithURICS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.PrimitiveTypeRefCS;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TuplePartCS;
import org.eclipse.ocl.xtext.basecs.TupleTypeCS;
import org.eclipse.ocl.xtext.basecs.TypeParameterCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.basecs.WildcardTypeRefCS;
import org.eclipse.ocl.xtext.essentialoclcs.BooleanLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionLiteralPartCS;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionPatternCS;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionTypeCS;
import org.eclipse.ocl.xtext.essentialoclcs.ContextCS;
import org.eclipse.ocl.xtext.essentialoclcs.CurlyBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;
import org.eclipse.ocl.xtext.essentialoclcs.IfExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.IfThenExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.InfixExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.InvalidLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.LambdaLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.LetExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.LetVariableCS;
import org.eclipse.ocl.xtext.essentialoclcs.MapLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.MapLiteralPartCS;
import org.eclipse.ocl.xtext.essentialoclcs.MapTypeCS;
import org.eclipse.ocl.xtext.essentialoclcs.NameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.NavigatingArgCS;
import org.eclipse.ocl.xtext.essentialoclcs.NestedExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.NullLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.NumberLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.PatternExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.PrefixExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.RoundBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.SelfExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.ShadowPartCS;
import org.eclipse.ocl.xtext.essentialoclcs.SquareBracketedClauseCS;
import org.eclipse.ocl.xtext.essentialoclcs.StringLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.TupleLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.TupleLiteralPartCS;
import org.eclipse.ocl.xtext.essentialoclcs.TypeLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.TypeNameExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.UnlimitedNaturalLiteralExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.VariableCS;
import org.eclipse.ocl.xtext.oclinecore.serializer.OCLinEcoreSemanticSequencer;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreConstraintCS;
import org.eclipse.ocl.xtext.oclinecorecs.SysMLCS;
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS;
import org.eclipse.sme.frsl.services.FRSLGrammarAccess;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public abstract class AbstractFRSLSemanticSequencer extends OCLinEcoreSemanticSequencer {

	@Inject
	private FRSLGrammarAccess grammarAccess;

	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == BaseCSPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case BaseCSPackage.ANNOTATION_CS:
				sequence_AnnotationCS(context, (AnnotationCS) semanticObject);
				return;
			case BaseCSPackage.ATTRIBUTE_CS:
				sequence_AttributeCS(context, (AttributeCS) semanticObject);
				return;
			case BaseCSPackage.DATA_TYPE_CS:
				sequence_DataTypeCS(context, (DataTypeCS) semanticObject);
				return;
			case BaseCSPackage.DETAIL_CS:
				sequence_DetailCS(context, (DetailCS) semanticObject);
				return;
			case BaseCSPackage.DOCUMENTATION_CS:
				sequence_DocumentationCS(context, (DocumentationCS) semanticObject);
				return;
			case BaseCSPackage.ENUMERATION_CS:
				sequence_EnumerationCS(context, (EnumerationCS) semanticObject);
				return;
			case BaseCSPackage.ENUMERATION_LITERAL_CS:
				sequence_EnumerationLiteralCS(context, (EnumerationLiteralCS) semanticObject);
				return;
			case BaseCSPackage.IMPLICIT_OPPOSITE_CS:
				sequence_ImplicitOppositeCS(context, (ImplicitOppositeCS) semanticObject);
				return;
			case BaseCSPackage.IMPORT_CS:
				sequence_ImportCS(context, (ImportCS) semanticObject);
				return;
			case BaseCSPackage.MODEL_ELEMENT_REF_CS:
				if (rule == grammarAccess.getActorRefCSRule()) {
					sequence_ActorRefCS(context, (ModelElementRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getAssocRefCSRule()) {
					sequence_AssocRefCS(context, (ModelElementRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getExtensionPointRefCSRule()) {
					sequence_ExtensionPointRefCS(context, (ModelElementRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getModelElementRefCSRule()) {
					sequence_ModelElementRefCS(context, (ModelElementRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getNegObjVarCSRule()
						|| rule == grammarAccess.getObjVarRefCSRule()) {
					sequence_ObjVarRefCS(context, (ModelElementRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getStepRefCSRule()) {
					sequence_StepRefCS(context, (ModelElementRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getUsecaseRefCSRule()) {
					sequence_UsecaseRefCS(context, (ModelElementRefCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.MULTIPLICITY_BOUNDS_CS:
				if (rule == grammarAccess.getMultiplicityBoundsCSRule()) {
					sequence_MultiplicityBoundsCS(context, (MultiplicityBoundsCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getMultiplicityCSRule()) {
					sequence_MultiplicityBoundsCS_MultiplicityCS(context, (MultiplicityBoundsCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.MULTIPLICITY_STRING_CS:
				if (rule == grammarAccess.getMultiplicityCSRule()) {
					sequence_MultiplicityCS_MultiplicityStringCS(context, (MultiplicityStringCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getMultiplicityStringCSRule()) {
					sequence_MultiplicityStringCS(context, (MultiplicityStringCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.OPERATION_CS:
				sequence_OperationCS(context, (OperationCS) semanticObject);
				return;
			case BaseCSPackage.PACKAGE_CS:
				sequence_PackageCS(context, (PackageCS) semanticObject);
				return;
			case BaseCSPackage.PARAMETER_CS:
				sequence_ParameterCS(context, (ParameterCS) semanticObject);
				return;
			case BaseCSPackage.PATH_ELEMENT_CS:
				if (rule == grammarAccess.getFirstPathElementCSRule()) {
					sequence_FirstPathElementCS(context, (PathElementCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getNextPathElementCSRule()) {
					sequence_NextPathElementCS(context, (PathElementCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getURIFirstPathElementCSRule()) {
					sequence_URIFirstPathElementCS(context, (PathElementCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.PATH_ELEMENT_WITH_URICS:
				sequence_URIFirstPathElementCS(context, (PathElementWithURICS) semanticObject);
				return;
			case BaseCSPackage.PATH_NAME_CS:
				if (rule == grammarAccess.getPathNameCSRule()) {
					sequence_PathNameCS(context, (PathNameCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getSimplePathNameCSRule()) {
					sequence_SimplePathNameCS(context, (PathNameCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getURIPathNameCSRule()) {
					sequence_URIPathNameCS(context, (PathNameCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getUnreservedPathNameCSRule()) {
					sequence_UnreservedPathNameCS(context, (PathNameCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.PRIMITIVE_TYPE_REF_CS:
				if (rule == grammarAccess.getTypedRefCSRule()
						|| rule == grammarAccess.getPrimitiveTypeCSRule()
						|| rule == grammarAccess.getTypeLiteralCSRule()
						|| rule == grammarAccess.getTypeExpWithoutMultiplicityCSRule()
						|| rule == grammarAccess.getTypeRefCSRule()) {
					sequence_PrimitiveTypeCS(context, (PrimitiveTypeRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeExpCSRule()) {
					sequence_PrimitiveTypeCS_TypeExpCS(context, (PrimitiveTypeRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeLiteralWithMultiplicityCSRule()) {
					sequence_PrimitiveTypeCS_TypeLiteralWithMultiplicityCS(context, (PrimitiveTypeRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypedMultiplicityRefCSRule()) {
					sequence_PrimitiveTypeCS_TypedMultiplicityRefCS(context, (PrimitiveTypeRefCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.REFERENCE_CS:
				if (rule == grammarAccess.getAssocEndCSRule()) {
					sequence_AssocEndCS(context, (ReferenceCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getModelElementCSRule()
						|| rule == grammarAccess.getReferenceCSRule()
						|| rule == grammarAccess.getStructuralFeatureCSRule()) {
					sequence_ReferenceCS(context, (ReferenceCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.STRUCTURED_CLASS_CS:
				sequence_StructuredClassCS(context, (StructuredClassCS) semanticObject);
				return;
			case BaseCSPackage.TEMPLATE_BINDING_CS:
				sequence_TemplateBindingCS(context, (TemplateBindingCS) semanticObject);
				return;
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS:
				sequence_TemplateParameterSubstitutionCS(context, (TemplateParameterSubstitutionCS) semanticObject);
				return;
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS:
				sequence_TemplateSignatureCS(context, (TemplateSignatureCS) semanticObject);
				return;
			case BaseCSPackage.TUPLE_PART_CS:
				sequence_TuplePartCS(context, (TuplePartCS) semanticObject);
				return;
			case BaseCSPackage.TUPLE_TYPE_CS:
				if (rule == grammarAccess.getTypedRefCSRule()
						|| rule == grammarAccess.getTupleTypeCSRule()
						|| rule == grammarAccess.getTypeLiteralCSRule()
						|| rule == grammarAccess.getTypeExpWithoutMultiplicityCSRule()
						|| rule == grammarAccess.getTypeRefCSRule()) {
					sequence_TupleTypeCS(context, (TupleTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeExpCSRule()) {
					sequence_TupleTypeCS_TypeExpCS(context, (TupleTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeLiteralWithMultiplicityCSRule()) {
					sequence_TupleTypeCS_TypeLiteralWithMultiplicityCS(context, (TupleTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypedMultiplicityRefCSRule()) {
					sequence_TupleTypeCS_TypedMultiplicityRefCS(context, (TupleTypeCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.TYPE_PARAMETER_CS:
				sequence_TypeParameterCS(context, (TypeParameterCS) semanticObject);
				return;
			case BaseCSPackage.TYPED_TYPE_REF_CS:
				if (rule == grammarAccess.getTypedMultiplicityRefCSRule()) {
					sequence_TypedMultiplicityRefCS_TypedTypeRefCS(context, (TypedTypeRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypedTupeMultiplicityRefCSRule()) {
					sequence_TypedTupeMultiplicityRefCS_TypedTypeRefCS(context, (TypedTypeRefCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypedRefCSRule()
						|| rule == grammarAccess.getTypedTypeRefCSRule()
						|| rule == grammarAccess.getTypeRefCSRule()) {
					sequence_TypedTypeRefCS(context, (TypedTypeRefCS) semanticObject);
					return;
				}
				else break;
			case BaseCSPackage.WILDCARD_TYPE_REF_CS:
				sequence_WildcardTypeRefCS(context, (WildcardTypeRefCS) semanticObject);
				return;
			}
		else if (epackage == EssentialOCLCSPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case EssentialOCLCSPackage.BOOLEAN_LITERAL_EXP_CS:
				sequence_BooleanLiteralExpCS(context, (BooleanLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.COLLECTION_LITERAL_EXP_CS:
				sequence_CollectionLiteralExpCS(context, (CollectionLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.COLLECTION_LITERAL_PART_CS:
				sequence_CollectionLiteralPartCS(context, (CollectionLiteralPartCS) semanticObject);
				return;
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS:
				if (rule == grammarAccess.getCollectionPatternCSRule()
						|| rule == grammarAccess.getTypeExpWithoutMultiplicityCSRule()) {
					sequence_CollectionPatternCS(context, (CollectionPatternCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeExpCSRule()) {
					sequence_CollectionPatternCS_TypeExpCS(context, (CollectionPatternCS) semanticObject);
					return;
				}
				else break;
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS:
				if (rule == grammarAccess.getTypedRefCSRule()
						|| rule == grammarAccess.getCollectionTypeCSRule()
						|| rule == grammarAccess.getTypeLiteralCSRule()
						|| rule == grammarAccess.getTypeExpWithoutMultiplicityCSRule()
						|| rule == grammarAccess.getTypeRefCSRule()) {
					sequence_CollectionTypeCS(context, (CollectionTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeExpCSRule()) {
					sequence_CollectionTypeCS_TypeExpCS(context, (CollectionTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeLiteralWithMultiplicityCSRule()) {
					sequence_CollectionTypeCS_TypeLiteralWithMultiplicityCS(context, (CollectionTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypedMultiplicityRefCSRule()) {
					sequence_CollectionTypeCS_TypedMultiplicityRefCS(context, (CollectionTypeCS) semanticObject);
					return;
				}
				else break;
			case EssentialOCLCSPackage.CONTEXT_CS:
				sequence_Model(context, (ContextCS) semanticObject);
				return;
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS:
				sequence_CurlyBracketedClauseCS(context, (CurlyBracketedClauseCS) semanticObject);
				return;
			case EssentialOCLCSPackage.EXP_SPECIFICATION_CS:
				sequence_SpecificationCS(context, (ExpSpecificationCS) semanticObject);
				return;
			case EssentialOCLCSPackage.IF_EXP_CS:
				sequence_IfExpCS(context, (IfExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.IF_THEN_EXP_CS:
				sequence_ElseIfThenExpCS(context, (IfThenExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.INFIX_EXP_CS:
				sequence_ExpCS(context, (InfixExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.INVALID_LITERAL_EXP_CS:
				sequence_InvalidLiteralExpCS(context, (InvalidLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS:
				sequence_LambdaLiteralExpCS(context, (LambdaLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.LET_EXP_CS:
				sequence_LetExpCS(context, (LetExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.LET_VARIABLE_CS:
				sequence_LetVariableCS(context, (LetVariableCS) semanticObject);
				return;
			case EssentialOCLCSPackage.MAP_LITERAL_EXP_CS:
				sequence_MapLiteralExpCS(context, (MapLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.MAP_LITERAL_PART_CS:
				sequence_MapLiteralPartCS(context, (MapLiteralPartCS) semanticObject);
				return;
			case EssentialOCLCSPackage.MAP_TYPE_CS:
				if (rule == grammarAccess.getTypedRefCSRule()
						|| rule == grammarAccess.getMapTypeCSRule()
						|| rule == grammarAccess.getTypeLiteralCSRule()
						|| rule == grammarAccess.getTypeExpWithoutMultiplicityCSRule()
						|| rule == grammarAccess.getTypeRefCSRule()) {
					sequence_MapTypeCS(context, (MapTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeExpCSRule()) {
					sequence_MapTypeCS_TypeExpCS(context, (MapTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeLiteralWithMultiplicityCSRule()) {
					sequence_MapTypeCS_TypeLiteralWithMultiplicityCS(context, (MapTypeCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypedMultiplicityRefCSRule()) {
					sequence_MapTypeCS_TypedMultiplicityRefCS(context, (MapTypeCS) semanticObject);
					return;
				}
				else break;
			case EssentialOCLCSPackage.NAME_EXP_CS:
				sequence_NameExpCS(context, (NameExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS:
				if (rule == grammarAccess.getNavigatingArgCSRule()) {
					sequence_NavigatingArgCS(context, (NavigatingArgCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getNavigatingBarArgCSRule()) {
					sequence_NavigatingBarArgCS(context, (NavigatingArgCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getNavigatingCommaArgCSRule()) {
					sequence_NavigatingCommaArgCS(context, (NavigatingArgCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getNavigatingSemiArgCSRule()) {
					sequence_NavigatingSemiArgCS(context, (NavigatingArgCS) semanticObject);
					return;
				}
				else break;
			case EssentialOCLCSPackage.NESTED_EXP_CS:
				sequence_NestedExpCS(context, (NestedExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.NULL_LITERAL_EXP_CS:
				sequence_NullLiteralExpCS(context, (NullLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.NUMBER_LITERAL_EXP_CS:
				sequence_NumberLiteralExpCS(context, (NumberLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.PATTERN_EXP_CS:
				sequence_PatternExpCS(context, (PatternExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.PREFIX_EXP_CS:
				if (rule == grammarAccess.getPrefixedLetExpCSRule()) {
					sequence_PrefixedLetExpCS(context, (PrefixExpCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getExpCSRule()
						|| rule == grammarAccess.getNavigatingArgExpCSRule()) {
					sequence_PrefixedLetExpCS_PrefixedPrimaryExpCS(context, (PrefixExpCS) semanticObject);
					return;
				}
				else if (action == grammarAccess.getExpCSAccess().getInfixExpCSOwnedLeftAction_0_1_0()
						|| rule == grammarAccess.getPrefixedPrimaryExpCSRule()) {
					sequence_PrefixedPrimaryExpCS(context, (PrefixExpCS) semanticObject);
					return;
				}
				else break;
			case EssentialOCLCSPackage.ROUND_BRACKETED_CLAUSE_CS:
				sequence_RoundBracketedClauseCS(context, (RoundBracketedClauseCS) semanticObject);
				return;
			case EssentialOCLCSPackage.SELF_EXP_CS:
				sequence_SelfExpCS(context, (SelfExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.SHADOW_PART_CS:
				sequence_ShadowPartCS(context, (ShadowPartCS) semanticObject);
				return;
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS:
				sequence_SquareBracketedClauseCS(context, (SquareBracketedClauseCS) semanticObject);
				return;
			case EssentialOCLCSPackage.STRING_LITERAL_EXP_CS:
				sequence_StringLiteralExpCS(context, (StringLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.TUPLE_LITERAL_EXP_CS:
				sequence_TupleLiteralExpCS(context, (TupleLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.TUPLE_LITERAL_PART_CS:
				sequence_TupleLiteralPartCS(context, (TupleLiteralPartCS) semanticObject);
				return;
			case EssentialOCLCSPackage.TYPE_LITERAL_EXP_CS:
				sequence_TypeLiteralExpCS(context, (TypeLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.TYPE_NAME_EXP_CS:
				if (rule == grammarAccess.getTypeExpCSRule()) {
					sequence_TypeExpCS_TypeNameExpCS(context, (TypeNameExpCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getTypeNameExpCSRule()
						|| rule == grammarAccess.getTypeExpWithoutMultiplicityCSRule()) {
					sequence_TypeNameExpCS(context, (TypeNameExpCS) semanticObject);
					return;
				}
				else break;
			case EssentialOCLCSPackage.UNLIMITED_NATURAL_LITERAL_EXP_CS:
				sequence_UnlimitedNaturalLiteralExpCS(context, (UnlimitedNaturalLiteralExpCS) semanticObject);
				return;
			case EssentialOCLCSPackage.VARIABLE_CS:
				sequence_CoIteratorVariableCS(context, (VariableCS) semanticObject);
				return;
			}
		else if (epackage == FrslCSPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case FrslCSPackage.ACT_STEP_CS:
				if (rule == grammarAccess.getActorStepCSRule()) {
					sequence_ActorStepCS(context, (ActStepCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getStepCSRule()) {
					sequence_ActorStepCS_StepCS_SystemStepCS(context, (ActStepCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getActStepCSRule()) {
					sequence_ActorStepCS_SystemStepCS(context, (ActStepCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getSystemStepCSRule()) {
					sequence_SystemStepCS(context, (ActStepCS) semanticObject);
					return;
				}
				else break;
			case FrslCSPackage.ACTOR_ACTION_CS:
				sequence_InputActionCS(context, (ActorActionCS) semanticObject);
				return;
			case FrslCSPackage.ACTOR_CS:
				sequence_ActorCS(context, (ActorCS) semanticObject);
				return;
			case FrslCSPackage.ALT_FLOW_CS:
				sequence_AltFlowCS(context, (AltFlowCS) semanticObject);
				return;
			case FrslCSPackage.ASSOCIATION_CS:
				sequence_AssociationCS(context, (AssociationCS) semanticObject);
				return;
			case FrslCSPackage.CONSTRAINT_CS:
				sequence_ConstraintCS(context, (ConstraintCS) semanticObject);
				return;
			case FrslCSPackage.EXTEND_CS:
				sequence_UcExtendCS(context, (ExtendCS) semanticObject);
				return;
			case FrslCSPackage.EXTENSION_POINT_CS:
				sequence_ExtensionPointCS(context, (ExtensionPointCS) semanticObject);
				return;
			case FrslCSPackage.FRSL_MODEL_CS:
				sequence_FrslModelCS(context, (FrslModelCS) semanticObject);
				return;
			case FrslCSPackage.OBJ_VAR_CS:
				sequence_ObjVarCS(context, (ObjVarCS) semanticObject);
				return;
			case FrslCSPackage.REJOIN_STEP_CS:
				if (rule == grammarAccess.getRejoinStepCSRule()) {
					sequence_RejoinStepCS(context, (RejoinStepCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getStepCSRule()) {
					sequence_RejoinStepCS_StepCS(context, (RejoinStepCS) semanticObject);
					return;
				}
				else break;
			case FrslCSPackage.SNAPSHOT_PATTERN_CS:
				sequence_SnapshotPatternCS(context, (SnapshotPatternCS) semanticObject);
				return;
			case FrslCSPackage.SYSTEM_ACTION_CS:
				sequence_OutputActionCS(context, (SystemActionCS) semanticObject);
				return;
			case FrslCSPackage.UC_STEP_CS:
				if (rule == grammarAccess.getStepCSRule()) {
					sequence_StepCS_UCStepCS(context, (UCStepCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getUCStepCSRule()) {
					sequence_UCStepCS(context, (UCStepCS) semanticObject);
					return;
				}
				else break;
			case FrslCSPackage.USECASE_CS:
				sequence_UsecaseCS(context, (UsecaseCS) semanticObject);
				return;
			case FrslCSPackage.USECASE_POSTCONDITION_CS:
				sequence_UsecasePostconditionCS(context, (UsecasePostconditionCS) semanticObject);
				return;
			case FrslCSPackage.USECASE_PRECONDITION_CS:
				sequence_UsecasePreconditionCS(context, (UsecasePreconditionCS) semanticObject);
				return;
			case FrslCSPackage.VAR_LINK_CS:
				sequence_VarLinkCS(context, (VarLinkCS) semanticObject);
				return;
			}
		else if (epackage == OCLinEcoreCSPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case OCLinEcoreCSPackage.OC_LIN_ECORE_CONSTRAINT_CS:
				if (rule == grammarAccess.getInvariantConstraintCSRule()) {
					sequence_InvariantConstraintCS(context, (OCLinEcoreConstraintCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getPostconditionConstraintCSRule()) {
					sequence_PostconditionConstraintCS(context, (OCLinEcoreConstraintCS) semanticObject);
					return;
				}
				else if (rule == grammarAccess.getPreconditionConstraintCSRule()) {
					sequence_PreconditionConstraintCS(context, (OCLinEcoreConstraintCS) semanticObject);
					return;
				}
				else break;
			case OCLinEcoreCSPackage.SYS_MLCS:
				sequence_SysMLCS(context, (SysMLCS) semanticObject);
				return;
			case OCLinEcoreCSPackage.TOP_LEVEL_CS:
				sequence_TopLevelCS(context, (TopLevelCS) semanticObject);
				return;
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}

	/**
	 * Contexts:
	 *     ActorCS returns ActorCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName description=StringLiteral?)
	 */
	protected void sequence_ActorCS(ISerializationContext context, ActorCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     ActorRefCS returns ModelElementRefCS
	 *
	 * Constraint:
	 *     ownedPathName=PathNameCS
	 */
	protected void sequence_ActorRefCS(ISerializationContext context, ModelElementRefCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getActorRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0(), semanticObject.getOwnedPathName());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     ActorStepCS returns ActStepCS
	 *
	 * Constraint:
	 *     (
	 *         isActorStep?='actStep'
	 *         name=UnrestrictedName?
	 *         description=StringLiteral?
	 *         preSnapshot=SnapshotPatternCS
	 *         postSnapshot=SnapshotPatternCS
	 *         actions+=ActorActionCS*
	 *     )
	 */
	protected void sequence_ActorStepCS(ISerializationContext context, ActStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     StepCS returns ActStepCS
	 *
	 * Constraint:
	 *     (
	 *         (
	 *             (
	 *                 isActorStep?='actStep'
	 *                 name=UnrestrictedName?
	 *                 description=StringLiteral?
	 *                 preSnapshot=SnapshotPatternCS
	 *                 postSnapshot=SnapshotPatternCS
	 *                 actions+=ActorActionCS*
	 *             ) |
	 *             (name=UnrestrictedName? description=StringLiteral? preSnapshot=SnapshotPatternCS postSnapshot=SnapshotPatternCS actions+=SystemActionCS*)
	 *         )
	 *         altFlows+=AltFlowCS*
	 *         nextStep=StepCS?
	 *     )
	 */
	protected void sequence_ActorStepCS_StepCS_SystemStepCS(ISerializationContext context, ActStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     ActStepCS returns ActStepCS
	 *
	 * Constraint:
	 *     (
	 *         (
	 *             isActorStep?='actStep'
	 *             name=UnrestrictedName?
	 *             description=StringLiteral?
	 *             preSnapshot=SnapshotPatternCS
	 *             postSnapshot=SnapshotPatternCS
	 *             actions+=ActorActionCS*
	 *         ) |
	 *         (name=UnrestrictedName? description=StringLiteral? preSnapshot=SnapshotPatternCS postSnapshot=SnapshotPatternCS actions+=SystemActionCS*)
	 *     )
	 */
	protected void sequence_ActorStepCS_SystemStepCS(ISerializationContext context, ActStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     AltFlowCS returns AltFlowCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName? baseStep=StepRefCS description=StringLiteral? condition=SnapshotPatternCS altStep=StepCS)
	 */
	protected void sequence_AltFlowCS(ISerializationContext context, AltFlowCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     AssocEndCS returns ReferenceCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName ownedType=TypedTupeMultiplicityRefCS)
	 */
	protected void sequence_AssocEndCS(ISerializationContext context, ReferenceCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME));
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAssocEndCSAccess().getNameUnrestrictedNameParserRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getAssocEndCSAccess().getOwnedTypeTypedTupeMultiplicityRefCSParserRuleCall_2_0(), semanticObject.getOwnedType());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     AssocRefCS returns ModelElementRefCS
	 *
	 * Constraint:
	 *     ownedPathName=PathNameCS
	 */
	protected void sequence_AssocRefCS(ISerializationContext context, ModelElementRefCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAssocRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0(), semanticObject.getOwnedPathName());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     AssociationCS returns AssociationCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName assocEnds+=AssocEndCS assocEnds+=AssocEndCS)
	 */
	protected void sequence_AssociationCS(ISerializationContext context, AssociationCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     ConstraintCS returns ConstraintCS
	 *
	 * Constraint:
	 *     constrExpr=SpecificationCS
	 */
	protected void sequence_ConstraintCS(ISerializationContext context, ConstraintCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FrslCSPackage.Literals.CONSTRAINT_CS__CONSTR_EXPR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FrslCSPackage.Literals.CONSTRAINT_CS__CONSTR_EXPR));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getConstraintCSAccess().getConstrExprSpecificationCSParserRuleCall_1_0(), semanticObject.getConstrExpr());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     ExtensionPointCS returns ExtensionPointCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName? (locations+=StepRefCS locations+=StepRefCS*)? description=StringLiteral? condition=SnapshotPatternCS?)
	 */
	protected void sequence_ExtensionPointCS(ISerializationContext context, ExtensionPointCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     ExtensionPointRefCS returns ModelElementRefCS
	 *
	 * Constraint:
	 *     ownedPathName=PathNameCS
	 */
	protected void sequence_ExtensionPointRefCS(ISerializationContext context, ModelElementRefCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExtensionPointRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0(), semanticObject.getOwnedPathName());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     FrslModelCS returns FrslModelCS
	 *
	 * Constraint:
	 *     (domainModel=TopLevelCS assocs+=AssociationCS* actors+=ActorCS* usecases+=UsecaseCS* ucExtends+=UcExtendCS*)
	 */
	protected void sequence_FrslModelCS(ISerializationContext context, FrslModelCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     ActorActionCS returns ActorActionCS
	 *     InputActionCS returns ActorActionCS
	 *
	 * Constraint:
	 *     (actor=ActorRefCS? objVars+=ObjVarCS+)
	 */
	protected void sequence_InputActionCS(ISerializationContext context, ActorActionCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     ObjVarCS returns ObjVarCS
	 *
	 * Constraint:
	 *     (isMatched?='$'? name=UnrestrictedName type=TypedRefCS)
	 */
	protected void sequence_ObjVarCS(ISerializationContext context, ObjVarCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     NegObjVarCS returns ModelElementRefCS
	 *     ObjVarRefCS returns ModelElementRefCS
	 *
	 * Constraint:
	 *     ownedPathName=PathNameCS
	 */
	protected void sequence_ObjVarRefCS(ISerializationContext context, ModelElementRefCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getObjVarRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0(), semanticObject.getOwnedPathName());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     SystemActionCS returns SystemActionCS
	 *     OutputActionCS returns SystemActionCS
	 *
	 * Constraint:
	 *     (actor=ActorRefCS (objVars+=ObjVarCS values+=SpecificationCS)+)
	 */
	protected void sequence_OutputActionCS(ISerializationContext context, SystemActionCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     RejoinStepCS returns RejoinStepCS
	 *
	 * Constraint:
	 *     (rejoinTo=StepRefCS description=StringLiteral? condition=SnapshotPatternCS?)
	 */
	protected void sequence_RejoinStepCS(ISerializationContext context, RejoinStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     StepCS returns RejoinStepCS
	 *
	 * Constraint:
	 *     (rejoinTo=StepRefCS description=StringLiteral? condition=SnapshotPatternCS? altFlows+=AltFlowCS* nextStep=StepCS?)
	 */
	protected void sequence_RejoinStepCS_StepCS(ISerializationContext context, RejoinStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     SnapshotPatternCS returns SnapshotPatternCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName? description=StringLiteral? (objects+=ObjVarCS | negObjects+=NegObjVarCS)* links+=VarLinkCS* constraints+=ConstraintCS*)
	 */
	protected void sequence_SnapshotPatternCS(ISerializationContext context, SnapshotPatternCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     StepCS returns UCStepCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName? description=StringLiteral? includedUC=UsecaseRefCS altFlows+=AltFlowCS* nextStep=StepCS?)
	 */
	protected void sequence_StepCS_UCStepCS(ISerializationContext context, UCStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     StepRefCS returns ModelElementRefCS
	 *
	 * Constraint:
	 *     ownedPathName=PathNameCS
	 */
	protected void sequence_StepRefCS(ISerializationContext context, ModelElementRefCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStepRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0(), semanticObject.getOwnedPathName());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     SystemStepCS returns ActStepCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName? description=StringLiteral? preSnapshot=SnapshotPatternCS postSnapshot=SnapshotPatternCS actions+=SystemActionCS*)
	 */
	protected void sequence_SystemStepCS(ISerializationContext context, ActStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     TypedTupeMultiplicityRefCS returns TypedTypeRefCS
	 *
	 * Constraint:
	 *     (ownedPathName=PathNameCS (ownedBinding=TemplateBindingCS | ownedBinding=TemplateBindingCS)? ownedMultiplicity=MultiplicityCS?)
	 */
	protected void sequence_TypedTupeMultiplicityRefCS_TypedTypeRefCS(ISerializationContext context, TypedTypeRefCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     UCStepCS returns UCStepCS
	 *
	 * Constraint:
	 *     (name=UnrestrictedName? description=StringLiteral? includedUC=UsecaseRefCS)
	 */
	protected void sequence_UCStepCS(ISerializationContext context, UCStepCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     UcExtendCS returns ExtendCS
	 *
	 * Constraint:
	 *     (extension=UsecaseRefCS extendedUC=UsecaseRefCS extPoints+=ExtensionPointRefCS extPoints+=ExtensionPointRefCS*)
	 */
	protected void sequence_UcExtendCS(ISerializationContext context, ExtendCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     UsecaseCS returns UsecaseCS
	 *
	 * Constraint:
	 *     (
	 *         name=UnrestrictedName
	 *         description=StringLiteral?
	 *         primaryActor=ActorRefCS
	 *         (secondaryActors+=ActorRefCS secondaryActors+=ActorRefCS*)?
	 *         precondition=UsecasePreconditionCS?
	 *         postcondition=UsecasePostconditionCS?
	 *         firstStep=StepCS
	 *         extPoints+=ExtensionPointCS*
	 *     )
	 */
	protected void sequence_UsecaseCS(ISerializationContext context, UsecaseCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     UsecasePostconditionCS returns UsecasePostconditionCS
	 *
	 * Constraint:
	 *     (description=StringLiteral? snapshot=SnapshotPatternCS)
	 */
	protected void sequence_UsecasePostconditionCS(ISerializationContext context, UsecasePostconditionCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     UsecasePreconditionCS returns UsecasePreconditionCS
	 *
	 * Constraint:
	 *     (description=StringLiteral? snapshot=SnapshotPatternCS)
	 */
	protected void sequence_UsecasePreconditionCS(ISerializationContext context, UsecasePreconditionCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


	/**
	 * Contexts:
	 *     UsecaseRefCS returns ModelElementRefCS
	 *
	 * Constraint:
	 *     ownedPathName=PathNameCS
	 */
	protected void sequence_UsecaseRefCS(ISerializationContext context, ModelElementRefCS semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUsecaseRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0(), semanticObject.getOwnedPathName());
		feeder.finish();
	}


	/**
	 * Contexts:
	 *     VarLinkCS returns VarLinkCS
	 *
	 * Constraint:
	 *     (isNeg?='!'? objVars+=ObjVarRefCS objVars+=ObjVarRefCS assoc=AssocRefCS)
	 */
	protected void sequence_VarLinkCS(ISerializationContext context, VarLinkCS semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}


}
