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
package org.eclipse.sme.frsl.cs2as;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.context.OperationContext;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.xtext.base.cs2as.BasicContinuation;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.*;
import org.eclipse.ocl.xtext.essentialocl.services.EssentialOCLGrammarAccess.TypeLiteralCSElements;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionTypeCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;
import org.eclipse.ocl.xtext.essentialoclcs.MapTypeCS;
import org.eclipse.sme.frsl.*;
import org.eclipse.sme.frsl.utilities.FRSLUtil;
import org.eclipse.sme.frslcs.*;
import org.eclipse.sme.frslcs.util.AbstractFrslCSPreOrderVisitor;

public class FRSLCSPreOrderVisitor extends AbstractFrslCSPreOrderVisitor{
	
	private static FrslFactory frslFactory =  FrslFactory.eINSTANCE;
	
	private Status status;
	private ILog log = Platform.getLog(getClass());

	public FRSLCSPreOrderVisitor(@NonNull CS2ASConversion context) {
		super(context);
	}
	
	@Override
	public @Nullable Continuation<?> visitActStepCS(org.eclipse.sme.frslcs.@NonNull ActStepCS actStepCS) {
		ActStep actStep = (ActStep) ( (FRSLCS2ASConversion) context ).getFrslElementAS(actStepCS);
			
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitActStepCS ***");
//		log.log(status);

		//*** Mapping the actStepCS to an operation of the usecase class
		@NonNull UsecaseCS usecaseCS = FRSLUtil.getUsecaseCS((FRSLCS2ASConversion) context, actStepCS);
		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();

		@NonNull EClass eClass_OPERATION = PivotPackage.Literals.OPERATION;		
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Operation.class, eClass_OPERATION, actStepCS);
		org.eclipse.ocl.pivot.Operation opActStep = (org.eclipse.ocl.pivot.Operation) actStepCS.getPivot();
		ucClass.getOwnedOperations().add(opActStep);
		opActStep.setName( "step" + FRSLUtil.getStepNumberString(actStep) + ( actStep.isIsActorStep() ? "_act" : "_sys" ) );		
		opActStep.setType( ( (FRSLCS2ASConversion) context ).getStandardLibrary().getBooleanType() );
		
		actStep.setName( opActStep.getName() + ":" + actStep.getName() );

		//***Mapping the objVars to attributes of the usecase class
		mapObjVar2Attr(usecaseCS, actStepCS.getPreSnapshot());
		mapObjVar2Attr(usecaseCS, actStepCS.getPostSnapshot());

		//TODO: support Graph transformation rules: Creating preconditions [obj == oclUndefined] for all obj that belongs to Post and does not belong to Pre.
		// posconditions [obj == oclUndefined] for all obj that belongs to Pre and does not belong to Post
		// similarly with links
		
		//FRSLUtil.refreshOpConstraint( context, helper, opActStep.getOwnedPreconditions(), actStepCS.getPreSnapshot() );
		//FRSLUtil.refreshOpConstraint( context, helper, opActStep.getOwnedPostconditions(), actStepCS.getPostSnapshot() );
		
		return visitStepCS(actStepCS);
	}
	
	@Override
	public @Nullable Continuation<?> visitActionCS(org.eclipse.sme.frslcs.@NonNull ActionCS csElement) {
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitActorActionCS(org.eclipse.sme.frslcs.@NonNull ActorActionCS actorActionCS) {
		ActorAction actorAction = (ActorAction) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actorActionCS );

		ModelElementRefCS actorRefCS = actorActionCS.getActor();
		ActorCS actorCS;
		ActStepCS stepCS = (ActStepCS) actorActionCS.eContainer();
		UsecaseCS usecaseCS = FRSLUtil.getUsecaseCS( (FRSLCS2ASConversion) context, stepCS);

		if(actorRefCS == null) {
			actorRefCS = usecaseCS.getPrimaryActor();
		}
		actorCS = (ActorCS) ( (FRSLCS2ASConversion) context ).lookupFrslElementCS( "ActorCS", actorRefCS.getOwnedPathName() );
		actorRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( actorCS.getPivot() );	

		Actor actor = (Actor) ( (FRSLCS2ASConversion) context ).getFrslElementAS(actorCS);
		actorAction.setActor(actor);
		
		for(ObjVarCS objVarCS:actorActionCS.getObjVars()) {
			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getFrslElementAS(objVarCS);
			objVar.setIsMatched(true);
			actorAction.getObjVars().add(objVar);
			
			if ( ( (ActStep) actorAction.eContainer() ).getPreSnapshot() != null){
				( (ActStep) actorAction.eContainer() ).getPreSnapshot().getObject().add(objVar);
				ObjVar postObjVar = frslFactory.createObjVar();
				postObjVar.setName( objVar.getName() );
				postObjVar.setType( objVar.getType() );
				( (ActStep) actorAction.eContainer() ).getPostSnapshot().getObject().add(postObjVar);
				( (FRSLCS2ASConversion) context ).frslConverter.frslCSPre2Post.put( objVar, postObjVar);
			}
			
		}
		
		return visitActionCS(actorActionCS);
	}

	@Override
	public @Nullable Continuation<?> visitActorCS(org.eclipse.sme.frslcs.@NonNull ActorCS csElement) {
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitAltFlowCS(org.eclipse.sme.frslcs.@NonNull AltFlowCS altFlowCS) {
		AltFlow altFlow = (AltFlow) ( (FRSLCS2ASConversion) context ).getFrslElementAS( altFlowCS );	
		
		UsecaseCS usecaseCS = (UsecaseCS) FRSLUtil.getUsecaseCS( (FRSLCS2ASConversion) context, (StepCS) altFlowCS.eContainer());
		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();
			
		ModelElementRefCS baseStepRefCS = altFlowCS.getBaseStep();
		
		if(baseStepRefCS.getOwnedPathName().getOwnedPathElements().size() != 1) {
			return null;
		}
		
		
		StepCS baseStepCS = (StepCS) ( (FRSLCS2ASConversion) context ).lookupStepCS( usecaseCS, baseStepRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString() );
		
		if(baseStepCS == null) {
			return null;
		}
		
		Step baseStep = (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS(baseStepCS);
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitAltFlowCS"
//				+ "\n***baseStep = " + (baseStep == null)
//				);
//		log.log(status);

		altFlow.setBaseStep( baseStep );
		
		//*** Mapping altFlowCS to the altFlow operation
		@NonNull EClass eClass_OPERATION = PivotPackage.Literals.OPERATION;	
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Operation.class, eClass_OPERATION, altFlowCS);
		org.eclipse.ocl.pivot.Operation opAltFlow = (org.eclipse.ocl.pivot.Operation) altFlowCS.getPivot();
		
		ucClass.getOwnedOperations().add(opAltFlow);
		opAltFlow.setName( "step" + FRSLUtil.getAtlFlowStepString(altFlow) + "_alt" );		
		opAltFlow.setType( ( (FRSLCS2ASConversion) context ).getStandardLibrary().getBooleanType() );
		
		altFlow.setName( opAltFlow.getName() );
		
		//FRSLUtil.refreshOpConstraint( context, helper, opAltFlow.getOwnedPreconditions(), altFlowCS.getCondition() );
		baseStepRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( opAltFlow );	
		
		//Mapping objVarCS to atttributes of the use case
		mapObjVar2Attr(usecaseCS, altFlowCS.getCondition());
		
		return null;
	}
	
	@Override
	public @Nullable Continuation<?> visitAssocEndCS(org.eclipse.sme.frslcs.@NonNull AssocEndCS csElement) {
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitAssociationCS(org.eclipse.sme.frslcs.@NonNull AssociationCS assocCS) {
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitConstraintCS(org.eclipse.sme.frslcs.@NonNull ConstraintCS csElement) {
		if (csElement.getConstrExpr() != null) {
			super.visitSpecificationCS(csElement.getConstrExpr());
		}

		return null;
	}

	@Override
	public @Nullable Continuation<?> visitExtendCS(org.eclipse.sme.frslcs.@NonNull ExtendCS extendCS) {
		Extend extend = (Extend) ( (FRSLCS2ASConversion) context ).getFrslElementAS( extendCS );

		ModelElementRefCS extendedUCRefCS = extendCS.getExtendedUC();
		UsecaseCS extendedUsecaseCS = (UsecaseCS) ( (FRSLCS2ASConversion) context ).lookupFrslElementCS( "UsecaseCS", extendedUCRefCS.getOwnedPathName() );
		Usecase extendedUsecase = (Usecase) ( (FRSLCS2ASConversion) context ).getFrslElementAS(extendedUsecaseCS);
		extend.setExtendedUC(extendedUsecase);
		extendedUCRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( extendedUsecaseCS.getPivot() );	

		ModelElementRefCS extensionUCRefCS = extendCS.getExtension();
		UsecaseCS extensionUCCS = (UsecaseCS) ( (FRSLCS2ASConversion) context ).lookupFrslElementCS( "UsecaseCS", extensionUCRefCS.getOwnedPathName() );
		Usecase extensionUC = (Usecase) ( (FRSLCS2ASConversion) context ).getFrslElementAS(extensionUCCS);
		extend.setExtension(extensionUC);
		extensionUCRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( extensionUCCS.getPivot() );	

		
		for(ModelElementRefCS extPointCSRef:extendCS.getExtPoints()) {
			ExtensionPointCS extPointCS = ( (FRSLCS2ASConversion) context ).lookupExtPointCS(extendedUsecaseCS, extPointCSRef);
			if(extPointCS != null) {
				ExtensionPoint extPoint = (ExtensionPoint) ( (FRSLCS2ASConversion) context ).getFrslElementAS(extPointCS);
				extend.getExtPoint().add(extPoint);	
				extPointCSRef.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( extPointCS.getPivot() );	
			}
		}
		
		org.eclipse.ocl.pivot.Class extendedUCClass = (org.eclipse.ocl.pivot.Class) ( extendedUsecaseCS ).getPivot();
		org.eclipse.ocl.pivot.Class extensionUCClass = (org.eclipse.ocl.pivot.Class) ( extensionUCCS ).getPivot();
		
		@NonNull EClass eClass_PROPERTY = PivotPackage.Literals.PROPERTY;		
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Property.class, eClass_PROPERTY, extendCS);
		org.eclipse.ocl.pivot.Property theExtendAttr  = (org.eclipse.ocl.pivot.Property) extendCS.getPivot();
		theExtendAttr.setName( "extendUC_" + extendedUsecaseCS.getName() );
		theExtendAttr.setType( extendedUCClass );
		extensionUCClass.getOwnedProperties().add(theExtendAttr);
		

		return null;
	}

	@Override
	public @Nullable Continuation<?> visitExtensionPointCS(org.eclipse.sme.frslcs.@NonNull ExtensionPointCS extPointCS) {
		ExtensionPoint extPoint = (ExtensionPoint) ( (FRSLCS2ASConversion) context ).getFrslElementAS( extPointCS );
		
		UsecaseCS usecaseCS = (UsecaseCS) extPointCS.eContainer();
		
		for(ModelElementRefCS stepRefCS: extPointCS.getLocations()) {
			
			if (stepRefCS.getOwnedPathName().getOwnedPathElements().size() == 1 ) {
				StepCS stepCS = ( (FRSLCS2ASConversion) context ).lookupStepCS( usecaseCS, stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString() );
//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitExtensionPointCS"
//						+ "\n***stepCS = " + (stepCS == null)
//						+ "\n***stepName = " + stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString()
//						);
//				log.log(status);
				
				if(stepCS != null) {
					Step step = (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( stepCS );
					extPoint.getLocation().add(step);
					stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( stepCS.getPivot() );	
				}
			} else if ( stepRefCS.getOwnedPathName().getOwnedPathElements().size() == 2 ){
				String firstName = stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString();	
				String secondName = stepRefCS.getOwnedPathName().getOwnedPathElements().get(1).toString();						
				
//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitExtensionPointCS"
//						+ "\n***allSymbol = " + allSymbol
//						+ "\n***stepName = " + stepName
//						);
//				log.log(status);
				
				// the syntax here is that: step01::all
				StepCS step01CS = ( (FRSLCS2ASConversion) context ).lookupStepCS( usecaseCS, firstName );
				if("all".equals( secondName ) && ( step01CS != null ) ) {
					Step step = (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( step01CS );
					extPoint.getLocation().add(step);
					stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( step01CS.getPivot() );	
					stepRefCS.getOwnedPathName().getOwnedPathElements().get(1).setReferredElement( step01CS.getPivot() );						
					while(step !=null) {
						extPoint.getLocation().add(step);
						step = step.getNextStep();
					}
				}else {
					// check the syntax step01::step02
					StepCS step02CS = ( (FRSLCS2ASConversion) context ).lookupStepCS( usecaseCS, secondName );
					if ( (step01CS != null) & (step02CS != null) ) {
						StepCS curStepCS = step01CS;
						int count = 0;
						while( (curStepCS != null) && !curStepCS.equals(step02CS)) {
							count++;
							curStepCS = curStepCS.getNextStep();
						}
						if(curStepCS != null) {
							stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( step01CS.getPivot() );	
							stepRefCS.getOwnedPathName().getOwnedPathElements().get(1).setReferredElement( step02CS.getPivot() );
							curStepCS = step01CS;
							for(int i = 0; i <= count; i++) {
								Step step = (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( curStepCS );
								extPoint.getLocation().add(step);
								curStepCS = curStepCS.getNextStep();
							}
						}
					}
				}
				
				
			}
		}	
		
		//Mapping objVarCS to atttributes of the use case
		mapObjVar2Attr(usecaseCS, extPointCS.getCondition());
		
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitFrslModelCS(org.eclipse.sme.frslcs.@NonNull FrslModelCS frslModelCS) {		
		return visitRootCS(frslModelCS);
	}

	@Override
	public @Nullable Continuation<?> visitObjVarCS(org.eclipse.sme.frslcs.@NonNull ObjVarCS objVarCS) {	
		return null;
	}

	public void refreshObjVarCS(org.eclipse.sme.frslcs.@NonNull ObjVarCS objVarCS) {	
		ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getFrslElementAS( objVarCS );
		
		@SuppressWarnings("null") @NonNull EClass eClass_PROPERTY = PivotPackage.Literals.PROPERTY;
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Property.class, eClass_PROPERTY, objVarCS);
		org.eclipse.ocl.pivot.Property newAttr = (org.eclipse.ocl.pivot.Property) objVarCS.getPivot();
		objVar.setType(newAttr);
		
		TypedRefCS objVarTypeCS = objVarCS.getType();
				
		Type objVarType = null;
		
		if(objVarTypeCS instanceof PrimitiveTypeRefCS) {
			objVarType = context.getStandardLibrary().getLibraryType(objVarTypeCS.toString());
		}else if (objVarTypeCS instanceof TypedTypeRefCS) {
			this.visitTypedTypeRefCS( (TypedTypeRefCS) objVarTypeCS);
			objVarType = ( (TypedTypeRefCS) objVarTypeCS ).getReferredType();	
		}else if (objVarTypeCS instanceof CollectionTypeCS) {
			CollectionTypeCS csElement = (CollectionTypeCS) objVarTypeCS;
			TypeRefCS csElementType =   csElement.getOwnedType();
			//**********************************
			//********* BEGIN ******************
			//( (CollectionTypeContinuation) this.visitCollectionTypeCS( (CollectionTypeCS) objVarTypeCS ) ).execute();
			//**********************************
			PivotMetamodelManager metamodelManager = context.getMetamodelManager();
			Type elementType = null;
			String name = csElement.getName();
			assert name != null;
			if (csElementType != null) {
				elementType = PivotUtil.getPivot(Type.class, csElementType);
				//check if elementType == a DomainClass
				if(elementType == null) {
					Model domainModel = context.getConverter().getASResource().getModel();
					for ( org.eclipse.ocl.pivot.Class curClass: domainModel.getOwnedPackages().get(0).getOwnedClasses() ) {
						if ( curClass.getName().equals( csElementType.toString() ) ) {
							elementType = curClass;
							break;
						}
					}				
				}
				//check if elementType == a PrimitiveType
				if(elementType == null) {
					if(csElementType instanceof PrimitiveTypeRefCS) {
						elementType = context.getStandardLibrary().getLibraryType(csElementType.toString());
					}
				}
				csElementType.setPivot( elementType );
				if (elementType != null) {
					boolean isNullFree;
					IntegerValue lowerValue;
					UnlimitedNaturalValue upperValue;
					MultiplicityCS csCollectionMultiplicity = csElement.getOwnedCollectionMultiplicity();
					if (csCollectionMultiplicity != null) {
						isNullFree = csCollectionMultiplicity.isIsNullFree();
						lowerValue = ValueUtil.integerValueOf(csCollectionMultiplicity.getLower());
						int upper = csCollectionMultiplicity.getUpper();
						upperValue = upper != -1 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE;
					}
					else {
						isNullFree = true;
						lowerValue = null;
						upperValue = null;
					}
					objVarType = metamodelManager.getCollectionType(name, elementType, isNullFree, lowerValue, upperValue);
					MultiplicityCS csMultiplicity = csElement.getOwnedMultiplicity();
					if (csMultiplicity != null) {
						int upper = csMultiplicity.getUpper();
						if ((upper <= -1) || (2 <= upper)) {
							isNullFree = csMultiplicity.isIsNullFree();
							lowerValue = ValueUtil.integerValueOf(csMultiplicity.getLower());
							upperValue = upper != -1 ? ValueUtil.unlimitedNaturalValueOf(upper) : ValueUtil.UNLIMITED_VALUE;
							objVarType = metamodelManager.getCollectionType(TypeId.SET_NAME, objVarType, isNullFree, lowerValue, upperValue);
						}
					}
				}
			}
			if (objVarType == null) {
				objVarType = metamodelManager.getStandardLibrary().getLibraryType(name);
			}
			objVarTypeCS.setPivot(objVarType);
			newAttr.setType(objVarType);
			//**********************************
			//********* END ******************
			//**********************************
			
			//( (CollectionType) objVarType).setElementType(elementType);

//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$refreshObjVarCS"
//					+ "\n***objVar = " + objVar.getName()
//					+ "\n***objVarCS.eContainer().eContainer().getClass() = " +  objVarCS.eContainer().eContainer().getClass()
//					+ "\n***objVarTypeCS.getPivot() = " + objVarTypeCS.getPivot()
//					+ "\n***elementTypeRefCS = " + csElementType.toString()
//					+ "\n***elementType = " + elementType
//					+ "\n***objVarType.getElementType = " + ( (CollectionType) objVarType).getElementType()
//					);
//			log.log(status);
			
//		}else if (objVarTypeCS instanceof MapTypeCS) {			
//			PivotMetamodelManager metamodelManager = context.getMetamodelManager();
//			TypedRefCS csKeyType = ( (MapTypeCS) csElement ).getOwnedKeyType();
//			TypedRefCS csValueType = ( (MapTypeCS) csElement ).getOwnedValueType();
//			String name = csElement.getName();
//			assert name != null;
//			if ((csKeyType != null) && (csValueType != null)) {
//				Boolean keysAreNullFree = context.isRequired(csKeyType);
//				Boolean valuesAreNullFree = context.isRequired(csValueType);
//				Type keyType = PivotUtil.getPivot(Type.class, csKeyType);
//				Type valueType = PivotUtil.getPivot(Type.class, csValueType);
//				if ((keyType != null) && (valueType != null)) {
//					objVarType = metamodelManager.getMapType(name, keyType, keysAreNullFree != Boolean.FALSE, valueType, valuesAreNullFree != Boolean.FALSE);
//				}
//			}
//			if (objVarType == null) {
//				objVarType = metamodelManager.getStandardLibrary().getLibraryType(name);
//			}
//			
//			objVarType = null;
//		}else if (objVarTypeCS instanceof TupleTypeCS) {
//			objVarType = null;
			
		}

//		objVar.setType(objVarType);
//		if( ( (FRSLCS2ASConversion) context ).getPostElement(objVar) != null ) {
//			ObjVar postObjVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getPostElement(objVar);
//			postObjVar.setType(objVarType);
//		}
		
		if( ( (FRSLCS2ASConversion) context ).getPostElement(objVar) != null ) {
			ObjVar postObjVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getPostElement(objVar);
			postObjVar.setType(newAttr);
		}
		
		
		//asElement.setType( context.getMetamodelManager().getOclType(csElement.getType().getDescription()) );
		//context.lookupType(csElement, null)
		
				
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitObjVarCS\n"
//				//+ "" + 
//				+ "\n***csElement = " + csElement
//				+ "\n***type = " + objVarType
//				+ "\n***objVarTypeCS = " + objVarTypeCS
//				+ "\n***objVarTypeCS.getDescription() = " + objVarTypeCS.getDescription()
//				+ "\n***objVarTypeCS.getOwnedMultiplicity() = " + objVarTypeCS.getOwnedMultiplicity()
//				+ "\n***objVarTypeCS.getPivot() = " + objVarTypeCS.getPivot()				);
//		log.log(status);
		
	
		//return objVarType;
	}
	

	@Override
	public @Nullable Continuation<?> visitRejoinStepCS(org.eclipse.sme.frslcs.@NonNull RejoinStepCS rejoinStepCS) {
		RejoinStep rejoinStep = (RejoinStep) ( (FRSLCS2ASConversion) context ).getFrslElementAS( rejoinStepCS );	
		
		UsecaseCS usecaseCS = (UsecaseCS) FRSLUtil.getUsecaseCS( (FRSLCS2ASConversion) context, rejoinStepCS);
		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();

		ModelElementRefCS stepRefCS = rejoinStepCS.getRejoinTo();
		
		if ( stepRefCS.getOwnedPathName().getOwnedPathElements().size() != 1) {
			return null;
		}
		
		
		StepCS stepCS = (StepCS) ( (FRSLCS2ASConversion) context ).lookupStepCS(usecaseCS, stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString() );
		
		if ( stepCS == null ) {
			return null;
		}
		
		Step step = (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( stepCS );
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitStepCS"
//				+ "\n***reJoinStep=" + rejoinStep.getName()
//				+ "\n***step=" + step
//				+ "\n***stepCS=" + stepCS
//				+ "\n***stepCS is null =" + (stepCS == null)
//				+ "\n***stepRefCS.getOwnedPathName() = " + stepRefCS.getOwnedPathName()
//				);
//		log.log(status);
//		
		rejoinStep.setRejoinTo(step);
		
		//*** Mapping rejoinStepCS to the rejoin operation
		@NonNull EClass eClass_OPERATION = PivotPackage.Literals.OPERATION;	
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Operation.class, eClass_OPERATION, rejoinStepCS);
		org.eclipse.ocl.pivot.Operation opRejoin = (org.eclipse.ocl.pivot.Operation) rejoinStepCS.getPivot();
		
		ucClass.getOwnedOperations().add(opRejoin);
		opRejoin.setName( "step" + FRSLUtil.getStepNumberString(rejoinStep) + "_rejn_step" + FRSLUtil.getRejoinStepString(rejoinStep) );		
		opRejoin.setType( ( (FRSLCS2ASConversion) context ).getStandardLibrary().getBooleanType() );
		
		rejoinStep.setName( opRejoin.getName() + ":" + opRejoin.getName() );
		
		//FRSLUtil.refreshOpConstraint( context, helper, opRejoin.getOwnedPreconditions(), rejoinStepCS.getCondition() );
		
		stepRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( opRejoin );	

		//Mapping objVarCS to atttributes of the use case
		mapObjVar2Attr(usecaseCS, rejoinStepCS.getCondition());

		return visitStepCS(rejoinStepCS);
	}

	@Override
	public @Nullable Continuation<?> visitSnapshotPatternCS(org.eclipse.sme.frslcs.@NonNull SnapshotPatternCS snapshotPatternCS) {		
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitStepCS(org.eclipse.sme.frslcs.@NonNull StepCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitSystemActionCS(org.eclipse.sme.frslcs.@NonNull SystemActionCS sysActionCS) {
		
		SystemAction sysAction = (SystemAction) ( (FRSLCS2ASConversion) context ).getFrslElementAS( sysActionCS );

		ModelElementRefCS actorRefCS = sysActionCS.getActor();
		ActorCS actorCS;
		
		ActStepCS stepCS = (ActStepCS) sysActionCS.eContainer();
		UsecaseCS usecaseCS = FRSLUtil.getUsecaseCS( (FRSLCS2ASConversion) context, stepCS);
		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();
		
		if(actorRefCS == null) {	
			actorRefCS = usecaseCS.getPrimaryActor();
		}
		actorCS = (ActorCS) ( (FRSLCS2ASConversion) context ).lookupFrslElementCS( "ActorCS", actorRefCS.getOwnedPathName() );
		actorRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( actorCS.getPivot() );	
		
		Actor actor = (Actor) ( (FRSLCS2ASConversion) context ).getFrslElementAS(actorCS);
		sysAction.setActor(actor);

		//int i = 0;
		//***Mapping objVars to attributes		
		
		for(ObjVarCS objVarCS:sysActionCS.getObjVars()) {
			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getFrslElementAS(objVarCS);
			sysAction.getObjVars().add(objVar);
			
			if ( ( (ActStep) sysAction.eContainer() ).getPostSnapshot() != null){
				( (ActStep) sysAction.eContainer() ).getPostSnapshot().getObject().add(objVar);
			}
			
			@SuppressWarnings("null") @NonNull EClass eClass_PROPERTY = PivotPackage.Literals.PROPERTY;		
			( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Property.class, eClass_PROPERTY, objVarCS);
			this.refreshObjVarCS(objVarCS);
			org.eclipse.ocl.pivot.Property newAttr = (org.eclipse.ocl.pivot.Property) objVarCS.getPivot();
			//newAttr.setName( "output_step" + FRSLUtil.getStepNumberString( (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS(stepCS ) ) + "_" + objVar.getName() );
			newAttr.setName(objVar.getName());
			ucClass.getOwnedProperties().add(newAttr);
			
//			ExpSpecificationCS oclExprCS = sysActionCS.getValues().get(i);	
//			i++;
//			
//			ExpressionInOCL asSpecification = PivotUtil.getPivot(ExpressionInOCL.class, oclExprCS);

//			newAttr.setOwnedExpression(asSpecification);			
//			
//			if (asSpecification != null) {
//				context.refreshContextVariable(oclExprCS, asSpecification);
//				ExpCS csExpression = oclExprCS.getOwnedExpression();
//				OCLExpression asExpression = csExpression != null ? context.visitLeft2Right(OCLExpression.class, csExpression) : null;
//				String statusText = csExpression != null ? ElementUtil.getExpressionText(csExpression) : "null";
//				PivotUtil.setBody(asSpecification, asExpression, statusText);
//				boolean isRequired = (asExpression != null) && asExpression.isIsRequired();
//				helper.setType(asSpecification, asExpression != null ? asExpression.getType() : null, isRequired);
//			}
			
//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitSystemActionCS\n"
//					+ "\n***objVar.getName = " + objVar.getName()
//					+ "\n***oclExprCS = " + oclExprCS.toString()
//					+ "\n***oclExprCS.getPivot() = " + oclExprCS.getPivot()
//					+ "\n***asSpecification = " + asSpecification
//					+ "\n***asSpecification.econtainer = " + asSpecification.eContainer()
//					);
//			log.log(status);
			
		}
		

		return visitActionCS(sysActionCS);
	}

	@Override
	public @Nullable Continuation<?> visitUCStepCS(org.eclipse.sme.frslcs.@NonNull UCStepCS ucStepCS) {
		UCStep ucStep = (UCStep) ( (FRSLCS2ASConversion) context ).getFrslElementAS( ucStepCS );
		ucStep.setName( "step" + FRSLUtil.getStepNumberString(ucStep) + "_uc:" + ucStepCS.getName() );
		return visitStepCS(ucStepCS);
	}

	@Override
	public @Nullable Continuation<?> visitUsecaseCS(org.eclipse.sme.frslcs.@NonNull UsecaseCS usecaseCS) {	
		Usecase usecase = (Usecase) ( (FRSLCS2ASConversion) context ).getFrslElementAS( usecaseCS );
		
		ModelElementRefCS primaryActorRefCS = usecaseCS.getPrimaryActor();
		ActorCS primaryActorCS = (ActorCS) ( (FRSLCS2ASConversion) context ).lookupFrslElementCS( "ActorCS", primaryActorRefCS.getOwnedPathName() );
		Actor primaryActor = (Actor) ( (FRSLCS2ASConversion) context ).getFrslElementAS(primaryActorCS);
		usecase.setPrimaryActor(primaryActor);
		primaryActorRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( primaryActorCS.getPivot() );
		
		for(ModelElementRefCS secondaryActorRefCS: usecaseCS.getSecondaryActors()) {
			ActorCS secondaryActorCS = (ActorCS) ( (FRSLCS2ASConversion) context ).lookupFrslElementCS( "ActorCS", secondaryActorRefCS.getOwnedPathName() );
			Actor secondaryActor = (Actor) ( (FRSLCS2ASConversion) context ).getFrslElementAS(secondaryActorCS);
			usecase.getSecondaryActor().add(secondaryActor);
			secondaryActorRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( secondaryActorCS.getPivot() );
		}
		
		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();		
		@NonNull EClass eClass_OPERATION = PivotPackage.Literals.OPERATION;	
		int extPointNum = 1;
		//*** Mapping each extPointCS to an extPoint operation
		for(ExtensionPointCS extPointCS: usecaseCS.getExtPoints()) {			
			( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Operation.class, eClass_OPERATION, extPointCS);
			org.eclipse.ocl.pivot.Operation opExtPoint = (org.eclipse.ocl.pivot.Operation) extPointCS.getPivot();
			ucClass.getOwnedOperations().add(opExtPoint);
			opExtPoint.setName( "opExtPoint" + extPointNum);	
			extPointNum++;
			opExtPoint.setType( ( (FRSLCS2ASConversion) context ).getStandardLibrary().getBooleanType() );
			
			//FRSLUtil.refreshOpConstraint( context, helper, opExtPoint.getOwnedPreconditions(), extPointCS.getCondition() );
			
		}
		
		
		return null;
	}
	
	@Override
	public @Nullable Continuation<?> visitUsecasePreconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePreconditionCS ucPreCS) {
		UsecaseCS usecaseCS = (UsecaseCS) ucPreCS.eContainer();
		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();
		
		//***Mapping the ucPrecondition to the enter_ operation
		@NonNull EClass eClass_OPERATION = PivotPackage.Literals.OPERATION;		
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Operation.class, eClass_OPERATION, ucPreCS);
		org.eclipse.ocl.pivot.Operation openter = (org.eclipse.ocl.pivot.Operation) ucPreCS.getPivot();
		ucClass.getOwnedOperations().add(openter);
		openter.setName( "enter_" + usecaseCS.getName() );		
		openter.setType( ( (FRSLCS2ASConversion) context ).getStandardLibrary().getBooleanType() );

		//***Mapping the objVars to attributes of the usecase class
		mapObjVar2Attr(usecaseCS, ucPreCS.getSnapshot());
		
		//TODO: support Graph transformation rules: Creating preconditions [obj == oclUndefined] for all obj that belongs to Post and does not belong to Pre.
		// posconditions [obj == oclUndefined] for all obj that belongs to Pre and does not belong to Post
		// similarly with links
		
//		//***Mapping the varLinks to part of the precondition of the enter_ operation
//		for(VarLinkCS varLinkCS : ucPreCS.getSnapshot().getLinks() ) {
//			VarLink varLink = (VarLink) ( (FRSLCS2ASConversion) context ).getFrslElementAS( varLinkCS );
//			
//			//TODO: we need to transform the varLinkCS to a Constraint
//			
////			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitUsecaseCS\n"
////					+ "\n***objVar.getName = " + objVar.getName()
////					+ "\n***objVar.getType = " + objVar.getType()
////					);
////			log.log(status);
//		}
				
//		FRSLUtil.refreshOpConstraint(context, helper, openter.getOwnedPreconditions(), ucPreCS.getSnapshot());
				
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitUsecasePostconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePostconditionCS ucPostCS) {
		UsecaseCS usecaseCS = (UsecaseCS) ucPostCS.eContainer();
		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();
		
		//***Mapping the ucPrecondition to the enter_ operation
		@NonNull EClass eClass_OPERATION = PivotPackage.Literals.OPERATION;		
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Operation.class, eClass_OPERATION, ucPostCS);
		org.eclipse.ocl.pivot.Operation opexit = (org.eclipse.ocl.pivot.Operation) ucPostCS.getPivot();
		ucClass.getOwnedOperations().add(opexit);
		opexit.setName( "exit_" + usecaseCS.getName() );		
		opexit.setType( ( (FRSLCS2ASConversion) context ).getStandardLibrary().getBooleanType() );

		//***Mapping the objVars to attributes of the usecase class
		mapObjVar2Attr(usecaseCS, ucPostCS.getSnapshot());
		//TODO: support Graph transformation rules: Creating preconditions [obj == oclUndefined] for all obj that belongs to Post and does not belong to Pre.
		// posconditions [obj == oclUndefined] for all obj that belongs to Pre and does not belong to Post
		// similarly with links

//		//***Mapping the varLinks to part of the postcondition of the exit_ operation
//		for(VarLinkCS varLinkCS : ucPostCS.getSnapshot().getLinks() ) {
//			VarLink varLink = (VarLink) ( (FRSLCS2ASConversion) context ).getFrslElementAS( varLinkCS );
//			
//			//TODO: we need to transform the varLinkCS to a Constraint
//			
////			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitUsecaseCS\n"
////					+ "\n***objVar.getName = " + objVar.getName()
////					+ "\n***objVar.getType = " + objVar.getType()
////					);
////			log.log(status);
//		}
		
		//FRSLUtil.refreshOpConstraint(context, helper, opexit.getOwnedPostconditions(), ucPostCS.getSnapshot());

		return null;
	}

	@Override
	public @Nullable Continuation<?> visitVarLinkCS(org.eclipse.sme.frslcs.@NonNull VarLinkCS varLinkCS) {
		VarLink varLink = (VarLink) ( (FRSLCS2ASConversion) context ).getFrslElementAS( varLinkCS );	

		ModelElementRefCS assocClassCS = varLinkCS.getAssoc();
		AssociationClass assocClass = (AssociationClass) ( (FRSLCS2ASConversion) context ).lookupFrslElementAS( "AssociationCS", assocClassCS.getOwnedPathName() );
		
		String assocClassName = assocClassCS.getOwnedPathName().getOwnedPathElements().get(0).toString();
		if("_Tracks".equals( assocClassName )) {
			Model domainModel = context.getConverter().getASResource().getModel();
			for ( org.eclipse.ocl.pivot.Class curClass: domainModel.getOwnedPackages().get(0).getOwnedClasses() ) {
				if ( (curClass instanceof AssociationClass) && (curClass.getName().equals("_Tracks") ) ) {
					assocClass = (AssociationClass) curClass;
				}
			}				
		}		

//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitSnapshotPatternCS\n"
//				+ "\n***domainModel = " + 		( (FRSLCS2ASConversion) context ).frslConverter.frslASResource.getContents().get(0)
//				);
//		log.log(status);
						
		EList<PathElementCS> pathElements = assocClassCS.getOwnedPathName().getOwnedPathElements();
		Element curElement = assocClass;
		int curPathElement = pathElements.size() - 1;
		while( ( curElement instanceof Element ) && (curPathElement >= 0) ) {			
			pathElements.get(curPathElement).setReferredElement( curElement );
			curElement = (Element) curElement.eContainer();
			curPathElement--;
		}
		
		varLink.setAssoc(assocClass);
	
		if( ( (FRSLCS2ASConversion) context ).getPostElement(varLink) != null ) {
			VarLink postVarLink = (VarLink) ( (FRSLCS2ASConversion) context ).getPostElement(varLink);
			postVarLink.setAssoc(assocClass);
		}
		
//		for(ModelElementRefCS objVarCS: varLinkCS.getObjVars()) {
//			SnapshotPatternCS curSnapshotCS = (SnapshotPatternCS) varLinkCS.eContainer();
//			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).lookupObjVarAS(curSnapshotCS, objVarCS );
//			ObjVar objVarPre = null;
//			if ( (objVar == null) && ( curSnapshotCS.eContainer() instanceof ActStepCS) ) {
//				ActStepCS curActStepCS = (ActStepCS) curSnapshotCS.eContainer();
//				if( curActStepCS.getPostSnapshot().equals(curSnapshotCS) ) {
//					objVarPre = (ObjVar) ( (FRSLCS2ASConversion) context ).lookupObjVarAS(curActStepCS.getPreSnapshot(), objVarCS);
//				}
//			}
//			if ( (objVar == null) && ( curSnapshotCS.eContainer() instanceof UsecasePostconditionCS) ) {
//				UsecasePostconditionCS ucPostCS = (UsecasePostconditionCS) curSnapshotCS.eContainer();
//				UsecasePreconditionCS ucPreCS = ( (UsecaseCS) ucPostCS.eContainer()).getPrecondition();
//				objVarPre = (ObjVar) ( (FRSLCS2ASConversion) context ).lookupObjVarAS(ucPreCS.getSnapshot(), objVarCS);
//			}
//			if(objVarPre != null) {
//				objVar = frslFactory.createObjVar();
//				objVar.setName( objVarPre.getName() );
//				objVar.setType( objVarPre.getType() );
//				SnapshotPattern curSnapshot = (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( curSnapshotCS );
//				curSnapshot.getObject().add(objVar);
//			}
//			if( objVar != null) {
//				varLink.getObjVar().add(objVar);
//			} else {
//				//TODO: let raise an error!!!				
//			}
//		}	
		return null;
	}

	public void mapObjVar2Attr(@NonNull UsecaseCS usecaseCS, SnapshotPatternCS snapshotPatternCS) {		
		if ( (usecaseCS == null) || (snapshotPatternCS == null) ){
			return;
		}
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$mapObjVar2Attr\n"
//				//+ "\n***objVar.getName = " + objVar.getName()
//				);
//		log.log(status);

		org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) ( usecaseCS ).getPivot();
		
		//***Mapping objVars to attributes		
		List<ObjVarCS> objVarList = new ArrayList<ObjVarCS>();
		objVarList.addAll( snapshotPatternCS.getObjects() );
		
		
		if ( snapshotPatternCS.eContainer() instanceof ActStepCS ) {
			ActStepCS actStepCS = (ActStepCS) snapshotPatternCS.eContainer();
			if ( actStepCS.isIsActorStep() && actStepCS.getActions() != null) {
				for(ActionCS actionCS: actStepCS.getActions()) {
					objVarList.addAll( actionCS.getObjVars() );
				}
			}
		}
		
		for(ObjVarCS objVarCS : objVarList ) {		
			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getFrslElementAS( objVarCS );
			boolean isNewAttr = true;
			for(Property curAttr: ucClass.getOwnedProperties() ) {
				if(curAttr.getName().equals( objVarCS.getName() ) ) {
					isNewAttr = false;
					objVarCS.setPivot(curAttr);					
					objVar.setType(curAttr);
					if( ( (FRSLCS2ASConversion) context ).getPostElement(objVar) != null ) {
						ObjVar postObjVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getPostElement(objVar);
						postObjVar.setType(curAttr);
					}			
									
					if (objVarCS.getType() instanceof CollectionTypeCS) {
						CollectionTypeCS objVarTypeCS = (CollectionTypeCS) objVarCS.getType();
						TypedRefCS elementTypeCS = objVarTypeCS.getOwnedType();
						objVarTypeCS.setPivot( curAttr.getType() );
						elementTypeCS.setPivot( ( (org.eclipse.ocl.pivot.CollectionType) curAttr.getType()).getElementType() );
					}
					break;
				}						
			}
			
			if( isNewAttr ) {				
				this.refreshObjVarCS(objVarCS);
				org.eclipse.ocl.pivot.Property newAttr = (org.eclipse.ocl.pivot.Property) objVarCS.getPivot();
				newAttr.setName( objVar.getName() );
				
//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$mapObjVar2Attr\n"
//						+ "\n***objVar.getName = " + objVar.getName()
//						+ "\n***objVar.getType() = " +  newAttr 
//						+ "\n***this.refreshObjVarCS(objVarCS) = " + this.refreshObjVarCS(objVarCS)
//				);
//				log.log(status);
				
				ucClass.getOwnedProperties().add(newAttr);							
				
//				if(objVar.getType() instanceof CollectionType) {
//					status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$mapObjVar2Attr\n"
//							+ "\n***objVar.getName = " + objVar.getName()
//							+ "\n***objVar.getType() = " +  (CollectionType) objVar.getType() 
//							+ "\n***objVar.getType().getElementType() = " + ( (CollectionType) objVar.getType() ).getElementType()
//					);
//					log.log(status);
//				}
				
			}
		}	
	}
	
//	public void visitFrslExpSpecificationCS(@NonNull ExpSpecificationCS csElement) {
//		StandardLibraryInternal standardLibrary = context.getMetamodelManager().getStandardLibrary();
//		EObject eContainer = csElement.eContainer();
//		if (eContainer instanceof ConstraintCS) {
//			org.eclipse.ocl.xtext.basecs.ConstraintCS csConstraint = (org.eclipse.ocl.xtext.basecs.ConstraintCS) eContainer;
//			SpecificationCS csStatusSpecification = csConstraint.getOwnedSpecification();
//			SpecificationCS csMessageSpecification = csConstraint.getOwnedMessageSpecification();
//			if ((csStatusSpecification != null) && (csMessageSpecification != null)) {
//				@NonNull TupleLiteralPart csTupleLiteralPart = context.refreshModelElement(TupleLiteralPart.class, PivotPackage.Literals.TUPLE_LITERAL_PART, csElement);
//				EStructuralFeature eContainingFeature = csElement.eContainingFeature();
//				if (eContainingFeature == BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION) {
//					csTupleLiteralPart.setName(PivotConstants.STATUS_PART_NAME);
//					csTupleLiteralPart.setType(standardLibrary.getBooleanType());
//				}
//				else if (eContainingFeature == BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION) {
//					csTupleLiteralPart.setName(PivotConstants.MESSAGE_PART_NAME);
//					csTupleLiteralPart.setType(standardLibrary.getStringType());
//				}
//				else {
//					//logger.error("unknown ExpSpecificationCS.eContainingFeature" + eContainingFeature);
//				}
//			}
//		}
//		context.refreshModelElement(ExpressionInOCL.class, PivotPackage.Literals.EXPRESSION_IN_OCL, csElement);
//	}
}