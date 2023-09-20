/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
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
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.cs2as.BaseCSContainmentVisitor;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.RootCS;
import org.eclipse.ocl.xtext.basecs.RootPackageCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;
import org.eclipse.sme.frsl.*;
import org.eclipse.sme.frsl.utilities.FRSLUtil;
import org.eclipse.sme.frslcs.*;
import org.eclipse.sme.frslcs.util.AbstractFrslCSContainmentVisitor;

public class FRSLCSContainmentVisitor extends AbstractFrslCSContainmentVisitor
{
	private Status status;
	private ILog log = Platform.getLog(getClass());
	
	private static FrslFactory frslFactory =  FrslFactory.eINSTANCE;
	
	public FRSLCSContainmentVisitor(@NonNull CS2ASConversion context) {	
		super(context);
	}
	
	@Override
	public @Nullable Continuation<?> visitActStepCS(org.eclipse.sme.frslcs.@NonNull ActStepCS actStepCS) {
		ActStep actStep = frslFactory.createActStep();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( actStepCS, (EObject) actStep );	
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitActStepCS ***"
//				+ "\n***actStepCS.name = " + actStepCS.getName()
//				+ "\n***actStepCS.isActorStep = " + actStepCS.isIsActorStep()
//				);
//		log.log(status);
		actStep.setIsActorStep(actStepCS.isIsActorStep());
		//actStep.setDescription(actStepCS.getDescription() );
		//actStep.setName(actStepCS.getName());
		
//		actStep.setPreSnapshot( (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actStepCS.getPreSnapshot() ) );
//		actStep.setPostSnapshot( (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actStepCS.getPostSnapshot() ) );	
		
		SnapshotPattern preSnapshot = (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actStepCS.getPreSnapshot() );
		actStep.setPreSnapshot( preSnapshot );
		SnapshotPattern postSnapshot = (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actStepCS.getPostSnapshot() );
		actStep.setPostSnapshot( postSnapshot );

		mapPre2PostSnapshot(preSnapshot, postSnapshot, actStepCS.getPostSnapshot().getNegObjects());
			
		for(ActionCS actionCS: actStepCS.getActions()) {
			actStep.getAction().add( (Action) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actionCS ) );
		}
		return visitStepCS(actStepCS);
	}

	@Override
	public @Nullable Continuation<?> visitActionCS(org.eclipse.sme.frslcs.@NonNull ActionCS actionCS) {
		Action action = (Action) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actionCS );
		action.setDescription( actionCS.getDescription() );
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitActorCS(org.eclipse.sme.frslcs.@NonNull ActorCS actorCS) {
		Actor actor = frslFactory.createActor();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( actorCS, (EObject) actor );
		actor.setName(actorCS.getName());	
		actor.setDescription( actorCS.getDescription() );

//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitActorCS"
//				+ "\n***primaryActor = "  + asElement.getName()
//				);
//		log.log(status);

		
		//@SuppressWarnings("null") @NonNull EClass eClass_NAMED_ELEMENT = PivotPackage.Literals.NAMED_ELEMENT;
		//( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.NamedElement.class, eClass_NAMED_ELEMENT, csElement);

//		UsecaseCS ucCS = (UsecaseCS) csElement.eContainer();
//		Usecase usecase = (Usecase) ( (FRSLCS2ASConversion) context ).getFrslElementAS( ucCS);
//		csElement.setPivot( usecase );

		return null;
	}

	@Override
	public @Nullable Continuation<?> visitActorActionCS(org.eclipse.sme.frslcs.@NonNull ActorActionCS actorActionCS) {
		ActorAction actorAction = (ActorAction) frslFactory.createActorAction();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( actorActionCS, (EObject) actorAction );
		actorAction.setType("inputAct");
		//actorAction.setActor(null);
		
		return visitActionCS(actorActionCS);
	}

	@Override
	public @Nullable Continuation<?> visitAltFlowCS(org.eclipse.sme.frslcs.@NonNull AltFlowCS altFlowCS) {
		AltFlow altFlow = (AltFlow) frslFactory.createAltFlow();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( altFlowCS, (EObject) altFlow );
		
		altFlow.setName(altFlowCS.getName());
		altFlow.setDescription(altFlowCS.getDescription());
		altFlow.setAltStep( (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( altFlowCS.getAltStep() ) );
		//altFlow.setBaseStep(null); let continue with the FRSLPreOrderVisitor
		altFlow.setCondition( (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( altFlowCS.getCondition() ) );
		
		return null;
	}
	
	@Override
	public @Nullable Continuation<?> visitAssocEndCS(org.eclipse.sme.frslcs.@NonNull AssocEndCS csElement) {
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitAssocEndCS"
//				+ "\n***" + csElement
//				//+ "property = " + PivotUtil.getPivot(Property.class, csElement) + "***\n"
//				);
//		log.log(status);
		
		//return visitReferenceCS(csElement);
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitAssociationCS(org.eclipse.sme.frslcs.@NonNull AssociationCS assocCS) {
		
		@SuppressWarnings("null") @NonNull EClass eClass = PivotPackage.Literals.ASSOCIATION_CLASS;
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(AssociationClass.class, eClass, assocCS);
		
		AssociationClass assocClass = ( (AssociationClass) assocCS.getPivot() );
		assocClass.setName( assocCS.getName() );
				
		for(ReferenceCS csRefCS: assocCS.getAssocEnds() ) {
//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$visitAssociationCS\n"
//					//+ "" + + "***\n"
//					+ "StepCS csIter = " + csRefCS.getPivot().getClass() + "***\n"
//					);
//			log.log(status);
			
			assocClass.getOwnedProperties().add( (Property) csRefCS.getPivot()  );

		}	
		
		( (FRSLCS2ASConversion) context ).refreshFrslElement( assocCS, (EObject) assocClass );
		
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitConstraintCS(org.eclipse.sme.frslcs.@NonNull ConstraintCS constraintCS) {
		@SuppressWarnings("null") @NonNull EClass eClass_CONSTRAINT = PivotPackage.Literals.CONSTRAINT;
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Constraint.class, eClass_CONSTRAINT, constraintCS);
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitConstraintCS"
//				+ "\n***constraint = " + (constraintCS.getPivot() ==null)
//				);
//		log.log(status);
		
		return null;
	}
	
	@Override
	public @Nullable Continuation<?> visitExtendCS(org.eclipse.sme.frslcs.@NonNull ExtendCS extendCS) {
		Extend extend = frslFactory.createExtend();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( extendCS, (EObject) extend );	
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitExtensionPointCS(org.eclipse.sme.frslcs.@NonNull ExtensionPointCS csElement) {
		ExtensionPoint asElement = frslFactory.createExtensionPoint();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( csElement, (EObject) asElement );		
		
		asElement.setCondition( (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( (SnapshotPatternCS) csElement.getCondition() ) );
		asElement.setDescription(csElement.getDescription());
		asElement.setName(csElement.getName());
		//asElement.setLocation(null);
		return null;
	}

	
	@Override
	public @Nullable Continuation<?> visitFrslModelCS(org.eclipse.sme.frslcs.@NonNull FrslModelCS frslModelCS) {
		FrslModel frslModel = (FrslModel) frslModelCS.getDomainModel().getPivot();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( frslModelCS, (EObject) frslModel );

//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitFrslModelCS *** Step01"
//				+ "\n FrslModel = " + frslModel.getName()
//				);
//		log.log(status);
		
		
		//***Mapping frslModel to a new package "use case"
		@NonNull EClass eClass_PACKAGE = PivotPackage.Literals.PACKAGE;	
		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Package.class, eClass_PACKAGE, frslModelCS);
		org.eclipse.ocl.pivot.Package usecasePackage  = (org.eclipse.ocl.pivot.Package) frslModelCS.getPivot();
		usecasePackage.setName("usecase");
		
		//***frslModel.setUcExtends(...);
		for(ExtendCS csIter: frslModelCS.getUcExtends()) {
			Extend asIter = (Extend) ( (FRSLCS2ASConversion) context ).getFrslElementAS( (ExtendCS) csIter );
			frslModel.getUcExtend().add(asIter);
		}
		
		//***frslModel.setUsecase(...);
		@NonNull EClass eClass_CLASS = PivotPackage.Literals.CLASS;
		for(UsecaseCS usecaseCS: frslModelCS.getUsecases()) {
			Usecase usecase = (Usecase) ( (FRSLCS2ASConversion) context ).getFrslElementAS( usecaseCS );
			frslModel.getUsecase().add(usecase);
			//***Mapping the usecase to a class
			( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Class.class, eClass_CLASS, usecaseCS);
			org.eclipse.ocl.pivot.Class ucClass = (org.eclipse.ocl.pivot.Class) usecaseCS.getPivot();
			ucClass.setName(usecase.getName());	
			ucClass.getSuperClasses().add( this.standardLibrary.getOclElementType() );
			usecasePackage.getOwnedClasses().add(ucClass);
		}
		//***frslModel.setAction(...);
		for(ActorCS actorCS: frslModelCS.getActors()) {
			Actor actor = (Actor) ( (FRSLCS2ASConversion) context ).getFrslElementAS( actorCS );
			frslModel.getActor().add(actor);
		}
		
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitObjVarCS(org.eclipse.sme.frslcs.@NonNull ObjVarCS objVarCS) {		
		ObjVar objVar = frslFactory.createObjVar();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( objVarCS, (EObject) objVar );
		objVar.setName(objVarCS.getName());
		objVar.setIsMatched(objVarCS.isIsMatched());
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitObjVarCS"
//				//+ "" + + "***\n"
//				+ "\n***newObjVar.hashCode = " + csElement.hashCode()
//				+ "\n***newObjVar.name = " + csElement.getName()
//				);
//		log.log(status);

		return null;
	}

	@Override
	public @Nullable Continuation<?> visitRejoinStepCS(org.eclipse.sme.frslcs.@NonNull RejoinStepCS rejoinStepCS) {
		RejoinStep rejoinStep = frslFactory.createRejoinStep();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( rejoinStepCS, (EObject) rejoinStep );
		rejoinStep.setDescription( rejoinStepCS.getDescription() );
		rejoinStep.setCondition( (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( rejoinStepCS.getCondition() ) );
		//rejoinStep.setRejoinTo( rejoinStepCS.getRejoinTo() ); let continue with the FRSLCSPreOrderVisitor ...
		
		return visitStepCS(rejoinStepCS);
	}

	@Override
	public @Nullable Continuation<?> visitSnapshotPatternCS(org.eclipse.sme.frslcs.@NonNull SnapshotPatternCS snapshotCS) {
		SnapshotPattern snapshot = frslFactory.createSnapshotPattern();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( snapshotCS, (EObject) snapshot );
		
		snapshot.setDescription(snapshotCS.getDescription());	
		snapshot.setName(snapshotCS.getName());
		
		//asElement.setObjVars(...);
		for(ObjVarCS objVarCS: snapshotCS.getObjects()) {
			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getFrslElementAS( objVarCS );
			snapshot.getObject().add(objVar);

//			if( objVarCS.getName().startsWith("_")) {
//				ConstraintCS isProblDomConstrCS = FrslCSFactory.eINSTANCE.createConstraintCS();
//				ExpSpecificationCS exprCS = org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSFactory.eINSTANCE.createExpSpecificationCS();
//				exprCS.setExprString("self." + objVar.getName() + ".isProblDom = true");
//				isProblDomConstrCS.setConstrExpr(exprCS);
//				snapshotCS.getConstraints().add(isProblDomConstrCS);
//			}

		}

//		//asElement.setNegObjVars(...);
//		for(ModelElementRefCS objVarRefCS: snapshotCS.getNegObjects()) {
//			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).lookupObjVarAS(snapshotCS, objVarRefCS );
//		}

		//asElement.setVarLink(...);
		for(VarLinkCS varlinkCS: snapshotCS.getLinks()) {
			VarLink varlink = (VarLink) ( (FRSLCS2ASConversion) context ).getFrslElementAS( varlinkCS );
			snapshot.getLink().add(varlink);
			
//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitSnapshotPatternCS"
//					+ "\n***varLink = " + csIter );
//			log.log(status);

			
		}
		
		//asElement.setConstraint(...);
		for(ConstraintCS constraintCS: snapshotCS.getConstraints()) {
			Constraint constraint = (Constraint) constraintCS.getPivot();
			snapshot.getConstraint().add(constraint);
		}		
		
		

				
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitStepCS(org.eclipse.sme.frslcs.@NonNull StepCS stepCS) {
		Step step  = ( (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( stepCS ) );
		step.setName( stepCS.getName() );
		step.setDescription( stepCS.getDescription() );
		
		step.setNextStep( (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( stepCS.getNextStep() ) );
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitStepCS"
//				+ "***step=" + step.getName()
//				+ "***nextStep=" + step.getNextStep() 
//				);
//		log.log(status);
		
		for( AltFlowCS altFlowCS: stepCS.getAltFlows() ) {
			step.getAltFlow().add( (AltFlow) ( (FRSLCS2ASConversion) context ).getFrslElementAS( altFlowCS )  );
		}	

		
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitSystemActionCS(org.eclipse.sme.frslcs.@NonNull SystemActionCS systemActionCS) {
		SystemAction systemAction = (SystemAction) frslFactory.createSystemAction();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( systemActionCS, (EObject) systemAction );
		systemAction.setType("outputAct");
		
		return visitActionCS(systemActionCS);
	}

	@Override
	public @Nullable Continuation<?> visitUCStepCS(org.eclipse.sme.frslcs.@NonNull UCStepCS ucStepCS) {
		UCStep ucStep = frslFactory.createUCStep();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( ucStepCS, (EObject) ucStep );	

		//ucStep.getIncludingUC();
		
		return visitStepCS(ucStepCS);
	}

	@Override
	public @Nullable Continuation<?> visitUsecaseCS(org.eclipse.sme.frslcs.@NonNull UsecaseCS usecaseCS) {
		Usecase usecase = frslFactory.createUsecase();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( usecaseCS, (EObject) usecase );
		usecase.setDescription(usecaseCS.getDescription());	
		usecase.setName(usecaseCS.getName());
		usecase.setFirstStep( (Step) ( (FRSLCS2ASConversion) context ).getFrslElementAS( usecaseCS.getFirstStep() ) );
		UsecasePrecondition usecasePrecondition = (UsecasePrecondition) ( (FRSLCS2ASConversion) context ).getFrslElementAS( usecaseCS.getPrecondition() );
		UsecasePostcondition usecasePostcondition = (UsecasePostcondition) ( (FRSLCS2ASConversion) context ).getFrslElementAS( usecaseCS.getPostcondition() );

//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitUsecaseCS" 
//				+ "\n***usecasePrecondition=" + usecasePrecondition
//				);
//		log.log(status);
		usecase.setPrecondition(usecasePrecondition );
		usecase.setPostcondition(usecasePostcondition);
		//asElement.setExtPoint(...);
		for(ExtensionPointCS extPointCS: usecaseCS.getExtPoints()) {
			ExtensionPoint extPoint = (ExtensionPoint) ( (FRSLCS2ASConversion) context ).getFrslElementAS( extPointCS );
			usecase.getExtPoint().add(extPoint);
		}
		//usecase.setPrimaryActor( primaryActor);
		//asElement.setSecondaryActor();
	
		return null;
	}
	
	@Override
	public @Nullable Continuation<?> visitUsecasePreconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePreconditionCS ucPreSnapshotCS) {
		UsecasePrecondition ucPreSnapshot = frslFactory.createUsecasePrecondition();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( ucPreSnapshotCS, (EObject) ucPreSnapshot );
		
		ucPreSnapshot.setDescription(ucPreSnapshotCS.getDescription());	
		ucPreSnapshot.setSnapshot( (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( ucPreSnapshotCS.getSnapshot() ) );	
		for(ObjVar objVar: ucPreSnapshot.getSnapshot().getObject() ) {
			objVar.setIsMatched(true);
		}

//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitUsecasePreconditionCS" 
//				+ "\n***usecasePrecondition=" + asElement
//				+ "\n***usecasePrecondition.getDescription=" + asElement.getDescription()
//				+ "\n***csElement.getDescription=" + csElement.getDescription()
//				);
//		log.log(status);

		return null;
	}

	@Override
	public @Nullable Continuation<?> visitUsecasePostconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePostconditionCS ucPostconditionCS) {
		UsecasePostcondition ucPostcondition = frslFactory.createUsecasePostcondition();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( ucPostconditionCS, (EObject) ucPostcondition );
		
		ucPostcondition.setDescription(ucPostconditionCS.getDescription());	
		ucPostcondition.setSnapshot( (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( (SnapshotPatternCS) ucPostconditionCS.getSnapshot() ) );	
		
		SnapshotPatternCS ucPreSnapshotCS = ( (UsecaseCS) ucPostconditionCS.eContainer() ).getPrecondition().getSnapshot();
		SnapshotPattern ucPreSnapshot = (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS(ucPreSnapshotCS);
		
		this.mapPre2PostSnapshot(ucPreSnapshot, ucPostcondition.getSnapshot(), ucPostconditionCS.getSnapshot().getNegObjects());

		return null;
	}

	
	@Override
	public @Nullable Continuation<?> visitVarLinkCS(org.eclipse.sme.frslcs.@NonNull VarLinkCS varLinkCS) {
		VarLink varLink = frslFactory.createVarLink();
		( (FRSLCS2ASConversion) context ).refreshFrslElement( varLinkCS, (EObject) varLink );
		varLink.setIsNeg(varLinkCS.isIsNeg());
		//varLink.setAssoc(null); continuing at FRSLCSPreOrderVisitor$visitVarLinkCS
		
		for(ModelElementRefCS objVarCS: varLinkCS.getObjVars()) {
			SnapshotPatternCS curSnapshotCS = (SnapshotPatternCS) varLinkCS.eContainer();
			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).lookupObjVarAS(curSnapshotCS, objVarCS );
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
			if( objVar != null) {
				varLink.getObjVar().add(objVar);
			} else {
				//TODO: let raise an error!!!				
			}
		}	

		
		return null;
	}	
	
	@Override
	public Continuation<?> visitTypedTypeRefCS(@NonNull TypedTypeRefCS csTypedTypeRef) {
		Continuation<?> ret = super.visitTypedTypeRefCS(csTypedTypeRef);

//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitTypedTypeRefCS" 
//				+ "\n***csTypedTypeRef=" + csTypedTypeRef
//				+ "\n***  csTypedTypeRef.getReferredType= " + csTypedTypeRef.getReferredType()
//				+ "\n***  csTypedTypeRef.eContainer()= " + csTypedTypeRef.eContainer().getClass()
//				+ "\n***  csTypedTypeRef.eContainer().eContainer()= " + csTypedTypeRef.eContainer().eContainer().getClass()
//				+ "\n***  csTypedTypeRef.eContainer().eContainer()= " + (csTypedTypeRef.eContainer().eContainer() instanceof AssociationCS)
//				);
//		log.log(status);

		EObject frslObject1 = csTypedTypeRef.eContainer();
		EObject frslObject2 = csTypedTypeRef.eContainer().eContainer();
		
		if ( (frslObject1 instanceof ObjVarCS) || (frslObject2 instanceof AssociationCS) ) {
			( (FRSLCS2ASConversion) this.context).frslConverter.allTypedTypeRefCS.add(csTypedTypeRef);
		}
		return ret;
	}	
	
//	@Override
//	public Continuation<?> visitTypedRefCS(@NonNull TypedRefCS csTypedRef) {
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitTypedRefCS" 
//				+ "\n***csTypedTypeRef=" + csTypedRef
//				+ "\n*** type = " + csTypedRef.getPivot() );
//		log.log(status);
//		return super.visitTypedRefCS(csTypedRef);
//	}
	
	public void mapPre2PostSnapshot(SnapshotPattern preSnapshot, SnapshotPattern postSnapshot, List<ModelElementRefCS> negObjVars) {
		SnapshotPatternCS preSnapshotCS = (SnapshotPatternCS) ( (FRSLCS2ASConversion) context ).getFrslElementCS(preSnapshot);
		for(VarLink postVarLink: postSnapshot.getLink()) {
			if ( postVarLink.getObjVar().size() == 0 ){
				VarLinkCS postVarLinkCS = (VarLinkCS) ( (FRSLCS2ASConversion) context ).getFrslElementCS(postVarLink);
				for(ModelElementRefCS objVarCS: postVarLinkCS.getObjVars()) {
					ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).lookupObjVarAS(preSnapshotCS, objVarCS );
					postVarLink.getObjVar().add(objVar);
				}
				
			} else if ( postVarLink.getObjVar().size() == 1 ){
				String postVarObjName = postVarLink.getObjVar().get(0).getName();
//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitActStepCS ***"
//						+ "\n***postVarObjName = " + postVarObjName
//						);
//				log.log(status);
				VarLinkCS postVarLinkCS = (VarLinkCS) ( (FRSLCS2ASConversion) context ).getFrslElementCS(postVarLink);
				int i = 0;
				for(ModelElementRefCS objVarRefCS: postVarLinkCS.getObjVars()) {
					String thisPostObjVarName = objVarRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString();
					if(postVarObjName.equals(thisPostObjVarName) ) {
						String thatPostObjVarName = postVarLinkCS.getObjVars().get(1 - i).getOwnedPathName().getOwnedPathElements().get(0).toString();
//						status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitActStepCS ***"
//								+ "\n***thatPostObjVarName = " + thatPostObjVarName
//								);
//						log.log(status);
						for( ObjVar preObjVar: preSnapshot.getObject()) {							
							if(preObjVar.getName().equals( thatPostObjVarName )) {
								ObjVar postObjVar = frslFactory.createObjVar();
								postObjVar.setName( preObjVar.getName() );
								//postObjVar.setType( preObjVar.getType() );
								postSnapshot.getObject().add(postObjVar);
								( (FRSLCS2ASConversion) context ).frslConverter.frslCSPre2Post.put( preObjVar, postObjVar);								
								postVarLink.getObjVar().add(1-i, postObjVar);
								break;
							}
						}
						break;
					}
					i++;
				}
			}
		}

		//Mapping the presnapshot to the postsnapshot includes the flowing steps.
		//*******************************************
		//*** Rule 1. if obj: preSnapshot and not obj:postSnapshot then creating obj:postSnapshot		
		//*******************************************
		for(ObjVar preObjVar: preSnapshot.getObject()) {		
			ObjVar newObjVar = preObjVar;
			for(ObjVar postObjVar: postSnapshot.getObject()) {
				if(postObjVar.getName().equals(preObjVar.getName()) ){
					newObjVar = null;
					break;
				}
			}
			if(newObjVar != null) {
				ObjVar postObjVar = frslFactory.createObjVar();
				postObjVar.setName( newObjVar.getName() );
				//postObjVar.setType( newObjVar.getType() );
				postSnapshot.getObject().add(postObjVar);
				( (FRSLCS2ASConversion) context ).frslConverter.frslCSPre2Post.put( newObjVar, postObjVar);
			}
		}
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitActStepCS *** Step02"
//				);
//		log.log(status);
		
		//*******************************************
		//*** Rule 2. if varLink: preSnapshot and not varLink:postSnapshot then creating varLink:postSnapshot
		//*******************************************
		
		for(VarLink preVarLink: preSnapshot.getLink()) {		
			VarLink newVarLink = preVarLink;
			for(VarLink postVarLink: postSnapshot.getLink()) {
				if ( FRSLUtil.isEqualVarLink(preVarLink, postVarLink) ){
					newVarLink = null;
					break;
				}
			}
			if ( newVarLink != null ) {
				VarLink postVarLink = frslFactory.createVarLink();
				postVarLink.setIsNeg( newVarLink.isIsNeg() );
				for(ObjVar preObjVar: newVarLink.getObjVar()) {
					for(ObjVar postObjVar: postSnapshot.getObject()) {
						if(postObjVar.getName().equals(preObjVar.getName())) {
							postVarLink.getObjVar().add(postObjVar);
							break;
						}
					}
				}
				postSnapshot.getLink().add(postVarLink);
				( (FRSLCS2ASConversion) context ).frslConverter.frslCSPre2Post.put( newVarLink, postVarLink);
			}
		}
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSContainmentVisitor$visitActStepCS *** Step03"
//				);
//		log.log(status);

		//*******************************************
		//*** Rule 3. if negObj:postSnapshot->obj then removed obj:postSnapshot and removed any varlink(...obj...): postSnapshot
		//*******************************************
		
		for(ModelElementRefCS objVarRefCS: negObjVars) {
			if( objVarRefCS.getOwnedPathName().getOwnedPathElements().size() != 1) {
				break;
			}
			String objVarName = objVarRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString();
			ObjVar deletedObjVar = null;
			for(ObjVar postObjVar: postSnapshot.getObject()) {
				if( postObjVar.getName().equals( objVarName ) ) {
					deletedObjVar = postObjVar;
					break;
				}
			}
			if( deletedObjVar != null) {
				postSnapshot.getObject().remove(deletedObjVar );
				List<VarLink> removedVarLinks = new ArrayList<VarLink>();
				for( VarLink postVarLink: postSnapshot.getLink() ) {
					if (postVarLink.getObjVar().contains(deletedObjVar)) {
						removedVarLinks.add(postVarLink);
					}
				}
				postSnapshot.getLink().removeAll(removedVarLinks);			
				
				//TODO: if the deleted object is not declared in the presnapshot then it is added to the presnapshot
//				ObjVar oldObjVar = null;
//				for(ObjVar preObjVar: preSnapshot.getObject()) {
//					if( deletedObjVar.getName().equals(preObjVar.getName() ) ){
//						oldObjVar = preObjVar;
//						break;
//					}
//				}
//				if(oldObjVar == null) {
//					ObjVar preObjVar = frslFactory.createObjVar();
//					preObjVar.setName( deletedObjVar.getName() );
//					//postObjVar.setType( newObjVar.getType() );
//					preSnapshot.getObject().add(preObjVar);
//				}				
			}
			
		}
		
		//*******************************************
		//*** Rule 4. if obj: postSnapshot and not obj: preSnapshot then obj.isMatched
		//*******************************************

		for( ObjVar postObjVar: postSnapshot.getObject() ) {
			boolean isNewObj = true;
			for(ObjVar preObjVar: preSnapshot.getObject()) {
				if( postObjVar.getName().equals(preObjVar.getName() ) ){
					isNewObj = false;
					break;
				}
			}
			postObjVar.setIsMatched(isNewObj);
		}

	}
	
	@Override
	public Continuation<?> visitRootPackageCS(@NonNull RootPackageCS csElement) {
		importPackages(csElement);
		@SuppressWarnings("null") @NonNull EClass eClass = org.eclipse.sme.frsl.FrslPackage.Literals.FRSL_MODEL; //PivotPackage.Literals.MODEL;
		Model root = refreshRootPackage(org.eclipse.sme.frsl.FrslModel.class, eClass, csElement);
		EList<ImportCS> csImports = csElement.getOwnedImports();
		if (csImports.size() > 0) {
			List<Import> newImports = new ArrayList<Import>(csImports.size());
			for (ImportCS csImport : csImports) {
				Import pivotElement = PivotUtil.getPivot(Import.class, csImport);
				if (pivotElement != null) {
					pivotElement.setImportedNamespace(csImport.getReferredNamespace());
				}
				newImports.add(pivotElement);
			}
			helper.refreshList(root.getOwnedImports(), newImports);
		}
		else {
			root.getOwnedImports().clear();
		}
		return null;
	}
}
