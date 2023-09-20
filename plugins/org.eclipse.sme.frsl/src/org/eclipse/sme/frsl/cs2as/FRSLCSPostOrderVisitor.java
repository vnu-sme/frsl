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

import java.util.Iterator;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.context.OperationContext;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityCS;
import org.eclipse.ocl.xtext.basecs.PrimitiveTypeRefCS;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.RootPackageCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.ocl.xtext.essentialoclcs.CollectionTypeCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;
import org.eclipse.sme.frsl.*;
import org.eclipse.sme.frsl.utilities.FRSLUtil;
import org.eclipse.sme.frslcs.*;
import org.eclipse.sme.frslcs.util.AbstractFrslCSPostOrderVisitor;

public class FRSLCSPostOrderVisitor extends AbstractFrslCSPostOrderVisitor{
	private Status status;
	private ILog log = Platform.getLog(getClass());

	public FRSLCSPostOrderVisitor(@NonNull CS2ASConversion context) {
		super(context);
	}

	//@Override
	public @Nullable Continuation<?> _visitActStepCS(org.eclipse.sme.frslcs.@NonNull ActStepCS actStepCS) {
		org.eclipse.ocl.pivot.Operation opActStep = (org.eclipse.ocl.pivot.Operation) actStepCS.getPivot();

		FRSLUtil.refreshOpConstraint( context, helper, opActStep.getOwnedPreconditions(), actStepCS.getPreSnapshot(), true);
		FRSLUtil.refreshOpConstraint( context, helper, opActStep.getOwnedPostconditions(), actStepCS.getPostSnapshot(), false);
		
		return visitStepCS(actStepCS);
	}
	
	@Override
	public @Nullable Continuation<?> visitActionCS(org.eclipse.sme.frslcs.@NonNull ActionCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitActorActionCS(org.eclipse.sme.frslcs.@NonNull ActorActionCS csElement) {
		return visitActionCS(csElement);
	}

	@Override
	public @Nullable Continuation<?> visitActorCS(org.eclipse.sme.frslcs.@NonNull ActorCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	//@Override
	public @Nullable Continuation<?> _visitAltFlowCS(org.eclipse.sme.frslcs.@NonNull AltFlowCS altFlowCS) {
		org.eclipse.ocl.pivot.Operation opAltFlow = (org.eclipse.ocl.pivot.Operation) altFlowCS.getPivot();		
		FRSLUtil.refreshOpConstraint( context, helper, opAltFlow.getOwnedPreconditions(), altFlowCS.getCondition(), true);		
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitAssociationCS(org.eclipse.sme.frslcs.@NonNull AssociationCS assocCS) {
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLUtil$visitAssociationCS"
//				);
//		log.log(status);

		return null;
	}

	

	@Override
	public @Nullable Continuation<?> visitConstraintCS(org.eclipse.sme.frslcs.@NonNull ConstraintCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitExtendCS(org.eclipse.sme.frslcs.@NonNull ExtendCS extendCS) {
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitExtensionPointCS(org.eclipse.sme.frslcs.@NonNull ExtensionPointCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitFrslModelCS(org.eclipse.sme.frslcs.@NonNull FrslModelCS frslModelCS) {

		
//		Orphanage orphanage = Orphanage.getOrphanage(this.context.getConverter().getASResource().getResourceSet());
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPostOrderVisitor$visitFrslModelCS"
//				+ "\n orphanage.size = " + orphanage.getOwnedClasses().size()
//				+ "\n set(A) = " + orphanage.getOwnedClasses().get(0)
//				);
//		log.log(status);		
		
		for( UsecaseCS usecaseCS: frslModelCS.getUsecases() ) {
			Iterator<EObject> frslIter = usecaseCS.eAllContents();
			while(frslIter.hasNext()) {
				ElementCS elementCS = (ElementCS) frslIter.next();
				if(elementCS instanceof ActStepCS) {
					_visitActStepCS( (ActStepCS) elementCS);
				} else if ( elementCS instanceof AltFlowCS) {
					_visitAltFlowCS( (AltFlowCS) elementCS  );
				} else if ( elementCS instanceof RejoinStepCS) {
					_visitRejoinStepCS( (RejoinStepCS) elementCS  );
				} else if ( elementCS instanceof UsecaseCS ) {
					_visitUsecaseCS( (UsecaseCS) elementCS  );
				} else if ( elementCS instanceof UsecasePostconditionCS ) {
					_visitUsecasePostconditionCS( (UsecasePostconditionCS) elementCS  );
				} else if ( elementCS instanceof UsecasePreconditionCS ) {
					_visitUsecasePreconditionCS( (UsecasePreconditionCS) elementCS  );
				} 
			}
		}
		
		return visitRootCS(frslModelCS);
	}

	
	@Override
	public @Nullable Continuation<?> visitObjVarCS(org.eclipse.sme.frslcs.@NonNull ObjVarCS objVarCS) {	
		//ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getFrslElementAS( objVarCS );
		
		org.eclipse.ocl.pivot.Property attr = (org.eclipse.ocl.pivot.Property) objVarCS.getPivot();
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPostOrderVisitor$visitObjVarCS\n"
//				+ "\n***objVarCS = " +  objVarCS.getName()
//				+ "\n***objVarCS.eContainer().eContainer().getClass() = " +  objVarCS.eContainer().eContainer().getClass()
//				//+ "\n***objVarCS.eContainer().eContainer().getName = " +  ( (StepCS) objVarCS.eContainer().eContainer() ).getName()
//				+ "\n***attr = " +  attr 
//				+ "\n***objVarCS.getType().getPivot() = " +  (Type) objVarCS.getType().getPivot()
//				);
//		log.log(status);
		
		if(attr != null) {
			if (objVarCS.getType() instanceof CollectionTypeCS) {		
				CollectionTypeCS objVarTypeCS = (CollectionTypeCS) objVarCS.getType();
				TypedRefCS elementTypeCS = objVarTypeCS.getOwnedType();
				attr.setType((Type) objVarTypeCS.getPivot());
				//( (org.eclipse.ocl.pivot.CollectionType) attr.getType()).setElementType((Type) elementTypeCS.getPivot() );				
			}else {
				attr.setType( (Type) objVarCS.getType().getPivot());
			}
		}		
		return null;
	}

	//@Override
	public @Nullable Continuation<?> _visitRejoinStepCS(org.eclipse.sme.frslcs.@NonNull RejoinStepCS rejoinStepCS) {
		org.eclipse.ocl.pivot.Operation opRejoin = (org.eclipse.ocl.pivot.Operation) rejoinStepCS.getPivot();		
		FRSLUtil.refreshOpConstraint( context, helper, opRejoin.getOwnedPreconditions(), rejoinStepCS.getCondition(), true);
		return visitStepCS(rejoinStepCS);
	}

//	@Override
//	public @Nullable Continuation<?> visitSnapshotPatternCS(org.eclipse.sme.frslcs.@NonNull SnapshotPatternCS snapshotCS) {
//		SnapshotPattern snapshot = (SnapshotPattern) ( (FRSLCS2ASConversion) context ).getFrslElementAS( snapshotCS );
//
//		@NonNull EClass eClass_CONSTRAINT = PivotPackage.Literals.CONSTRAINT;		
//		EFactory eFactoryConstrInstance = eClass_CONSTRAINT.getEPackage().getEFactoryInstance();
//
//		ExpressionInOCL asSpecification = null;		
//		org.eclipse.ocl.pivot.Operation curOperation;
//		ParserContext parserContext;
//		
//		for(ObjVarCS objVarCS: snapshotCS.getObjects()) {
//			ObjVar objVar = (ObjVar) ( (FRSLCS2ASConversion) context ).getFrslElementAS( objVarCS );			
//			if ( objVar.getName().startsWith("_") ){
//				String constrExpr = "self." + objVar.getName() + ".isProblDom = true";	
//				//String constrExpr = "name = 'hello'";
//				org.eclipse.ocl.pivot.Element tmpElement = ( (org.eclipse.ocl.xtext.basecs.ModelElementCS) objVarCS.eContainer().eContainer() ).getPivot();
//				if ( tmpElement instanceof org.eclipse.ocl.pivot.Operation ) {
//					curOperation = (org.eclipse.ocl.pivot.Operation) tmpElement ;
//					org.eclipse.ocl.pivot.Constraint theIsProblDomConstr  = (org.eclipse.ocl.pivot.Constraint) eFactoryConstrInstance.create(eClass_CONSTRAINT);
//					curOperation.getOwnedPreconditions().add(theIsProblDomConstr);
//					
//					status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPostOrderVisitor$visitSnapshotPatternCS"
//							+ "\n***curOperation = " + curOperation.getName()
//							+ "\n***curClass = " + curOperation.getOwningClass().getName()
//							);
//					log.log(status);
//
//					//parserContext = new OperationContext(context.getEnvironmentFactory(), null, curOperation, PivotConstants.RESULT_NAME);
//					parserContext = ( (EssentialOCLCSResource) context.getConverter().getCSResource() ).getParserContext();
//					try {
//						asSpecification = parserContext.parse(theIsProblDomConstr, constrExpr);
//					} catch (ParserException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}	
//					if( asSpecification != null) {
//						//org.eclipse.ocl.pivot.Constraint theIsProblDomConstr  = (org.eclipse.ocl.pivot.Constraint) eFactoryConstrInstance.create(eClass_CONSTRAINT);
//						theIsProblDomConstr.setOwnedSpecification(asSpecification);
//						snapshot.getConstraint().add(theIsProblDomConstr);
//					}
//				}
//			}
//		}
//
//		return null;
//	}

	@Override
	public @Nullable Continuation<?> visitStepCS(org.eclipse.sme.frslcs.@NonNull StepCS csElement) {
		//return visitModelElementCS(csElement);
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitSystemActionCS(org.eclipse.sme.frslcs.@NonNull SystemActionCS sysActionCS) {
		int i = 0;
		for(ObjVarCS objVarCS:sysActionCS.getObjVars()) {
			ExpSpecificationCS oclExprCS = sysActionCS.getValues().get(i);	
			i++;
			ExpressionInOCL asSpecification = PivotUtil.getPivot(ExpressionInOCL.class, oclExprCS);					
			org.eclipse.ocl.pivot.Property newAttr = (org.eclipse.ocl.pivot.Property) objVarCS.getPivot();
			newAttr.setOwnedExpression(asSpecification);			
			
			if (asSpecification != null) {
				context.refreshContextVariable(oclExprCS, asSpecification);
				ExpCS csExpression = oclExprCS.getOwnedExpression();
				OCLExpression asExpression = csExpression != null ? context.visitLeft2Right(OCLExpression.class, csExpression) : null;
				String statusText = csExpression != null ? ElementUtil.getExpressionText(csExpression) : "null";
				PivotUtil.setBody(asSpecification, asExpression, statusText);
				boolean isRequired = (asExpression != null) && asExpression.isIsRequired();
				helper.setType(asSpecification, asExpression != null ? asExpression.getType() : null, isRequired);
			}
			
		}
		return visitActionCS(sysActionCS);
	}

	@Override
	public @Nullable Continuation<?> visitUCStepCS(org.eclipse.sme.frslcs.@NonNull UCStepCS ucStepCS) {
	
		UCStep ucStep = (UCStep) ( (FRSLCS2ASConversion) context ).getFrslElementAS( ucStepCS );

		ModelElementRefCS includedUCRefCS = ucStepCS.getIncludedUC();
		UsecaseCS includedUsecaseCS = (UsecaseCS) ( (FRSLCS2ASConversion) context ).lookupFrslElementCS( "UsecaseCS", includedUCRefCS.getOwnedPathName() );
		Usecase includedUsecase = (Usecase) ( (FRSLCS2ASConversion) context ).getFrslElementAS(includedUsecaseCS);
		ucStep.setIncludedUC(includedUsecase);
		
		org.eclipse.ocl.pivot.Class includedUCClass = (org.eclipse.ocl.pivot.Class) ( includedUsecaseCS ).getPivot();
		includedUCRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( includedUCClass );

		UsecaseCS baseUCCS = (UsecaseCS) FRSLUtil.getUsecaseCS( (FRSLCS2ASConversion) context, ucStepCS);
		
		
		
		Usecase baseUC = (Usecase) ( (FRSLCS2ASConversion) context ).getFrslElementAS(baseUCCS);
		org.eclipse.ocl.pivot.Class baseUCClass = (org.eclipse.ocl.pivot.Class) ( baseUCCS ).getPivot();
	
		boolean isNewUCInclude = true;
		for ( Include ucInclude: baseUC.getInclude() ) {
			if( ucInclude.getAddition().equals(includedUsecase)) {
				isNewUCInclude = false;
				break;
			}
		}
		if(isNewUCInclude) {
			Include ucInclude = FrslFactory.eINSTANCE.createInclude();
			ucInclude.setIncludingUC(baseUC);
			ucInclude.setAddition(includedUsecase);

			//		//*** Mapping inclStepCS to the inclUC operation
			//		@NonNull EClass eClass_OPERATION = PivotPackage.Literals.OPERATION;	
			//		( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Operation.class, eClass_OPERATION, ucStepCS);
			//		org.eclipse.ocl.pivot.Operation opUCInclusion = (org.eclipse.ocl.pivot.Operation) ucStepCS.getPivot();
			//		
			//		ucClass.getOwnedOperations().add(opUCInclusion);
			//		opUCInclusion.setName( "step" + FRSLUtil.getStepNumberString( ucStep ) + "_usecaseIncl");	
			//		opUCInclusion.setType( ( (FRSLCS2ASConversion) context ).getStandardLibrary().getBooleanType() );
			//		
			//		UsecaseCS includedUsecaseCS = (UsecaseCS) ( (FRSLCS2ASConversion) context ).getFrslElementCS(includedUsecase);
			//		ucRefCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement( includedUsecaseCS.getPivot() );	

			@NonNull EClass eClass_PROPERTY = PivotPackage.Literals.PROPERTY;		
			( (FRSLCS2ASConversion) context ).getConverter().refreshModelElement(org.eclipse.ocl.pivot.Property.class, eClass_PROPERTY, ucStepCS);
			org.eclipse.ocl.pivot.Property theIncludedUCAttr  = (org.eclipse.ocl.pivot.Property) ucStepCS.getPivot();
			theIncludedUCAttr.setName( "includedUC_" + includedUsecase.getName() );
			theIncludedUCAttr.setType( includedUCClass );
			baseUCClass.getOwnedProperties().add(theIncludedUCAttr);
		}
		
		return visitStepCS(ucStepCS);
	}

	//@Override
	public @Nullable Continuation<?> _visitUsecaseCS(org.eclipse.sme.frslcs.@NonNull UsecaseCS usecaseCS) {

		for(ExtensionPointCS extPointCS: usecaseCS.getExtPoints()) {			
			org.eclipse.ocl.pivot.Operation opExtPoint = (org.eclipse.ocl.pivot.Operation) extPointCS.getPivot();			
			FRSLUtil.refreshOpConstraint( context, helper, opExtPoint.getOwnedPreconditions(), extPointCS.getCondition(), true );
		}
		
		return null;
	}

	//@Override
	public @Nullable Continuation<?> _visitUsecasePostconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePostconditionCS ucPostCS) {
		org.eclipse.ocl.pivot.Operation opexit = (org.eclipse.ocl.pivot.Operation) ucPostCS.getPivot();
		FRSLUtil.refreshOpConstraint(context, helper, opexit.getOwnedPostconditions(), ucPostCS.getSnapshot(), false);		
		return null;
	}

	//@Override
	public @Nullable Continuation<?> _visitUsecasePreconditionCS(org.eclipse.sme.frslcs.@NonNull UsecasePreconditionCS ucPreCS) {		
		org.eclipse.ocl.pivot.Operation openter = (org.eclipse.ocl.pivot.Operation) ucPreCS.getPivot();
		FRSLUtil.refreshOpConstraint(context, helper, openter.getOwnedPreconditions(), ucPreCS.getSnapshot(), true);
		return null;
	}

	@Override
	public @Nullable Continuation<?> visitVarLinkCS(org.eclipse.sme.frslcs.@NonNull VarLinkCS csElement) {
		
		VarLink varLink = (VarLink) ( (FRSLCS2ASConversion) context ).getFrslElementAS( (VarLinkCS) csElement );
		
		for(ModelElementRefCS objVarCS: csElement.getObjVars()) {
			
			org.eclipse.ocl.pivot.Property newAttr = (org.eclipse.ocl.pivot.Property) objVarCS.getPivot();
			objVarCS.getOwnedPathName().getOwnedPathElements().get(0).setReferredElement(newAttr);
			
			//newAttr.setType(objVar.getType());
			
//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPostOrderVisitor$visitVarLinkCS\n"
//					//+ "" + + "***\n"
//					+ "\n***objVarCS.getDescription = " + objVarCS.getDescription()
//					+ "\n***objVarCS.getOwnedPathName = " + objVarCS.getOwnedPathName()
//					+ "\n***objVarCS.getReferredElement = " + ( (ModelElementRefCS)objVarCS ).getReferredElement()
//					+ "\n***objVar = " + objVar.getName()
//					);
//			log.log(status);
			
		}	
		
		return null;
	}
	
	@Override
	public Continuation<?> visitReferenceCS(@NonNull ReferenceCS csReference) {
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPostOrderVisitor$visitReferenceCS\n"
//				//+ "" + + "***\n"
//				);
//		log.log(status);

		
		if( csReference.eContainer() instanceof AssociationCS ){
			AssociationCS assocClassCS = (AssociationCS) csReference.eContainer();
			@NonNull Property thisProperty = PivotUtil.getPivot(Property.class, csReference);
			@NonNull AssociationClass assocClass = (AssociationClass) assocClassCS.getPivot();

//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPostOrderVisitor$visitReferenceCS"
//					+ "\nAssocEnd=***" + csReference
//					+ "\n***AssocianCS = " + csReference.eContainer()
//					+ "\n***assocClass00 = " + assocClass
//					);
//			log.log(status);
			
			
			Property thatProperty = assocClass.getOwnedProperties().get(0);
			if ( thatProperty  == thisProperty ){
				thatProperty = assocClass.getOwnedProperties().get(1); 
			}
			thisProperty.setOpposite(thatProperty);
			thatProperty.setOpposite(thisProperty);
			
			org.eclipse.ocl.pivot.Model domainModel = (org.eclipse.ocl.pivot.Model) ( (FrslModelCS) assocClassCS.eContainer() ).getDomainModel().getPivot();
			FRSLUtil.installPackage(domainModel, assocClass);
			
			this.addNewAttr4AssocClass(assocClassCS);			

//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPostOrderVisitor$visitReferenceCS"
//					+ "\nAssocEnd=***" + csReference
//					+ "\n***AssocianCS = " + csReference.eContainer()
//					+ "\n***assocClass01 = " + assocClass
//					+ "\n***domainModel = " + domainModel.getClass()
//					);
//			log.log(status);
			
		}
				
		return super.visitReferenceCS(csReference);
	}
	
	public void addNewAttr4AssocClass(org.eclipse.sme.frslcs.@NonNull AssociationCS assocCS) {
		AssociationClass assocClass = (AssociationClass) ( (FRSLCS2ASConversion) context ).getFrslElementAS( assocCS );	

		
		@NonNull EClass eClass_PROPERTY = PivotPackage.Literals.PROPERTY;
		EFactory eFactoryAttrInstance = eClass_PROPERTY.getEPackage().getEFactoryInstance();
		
		// create the corresponding property of the class
		Property assocProp01 = assocClass.getOwnedProperties().get(0);
		Property assocProp02 = assocClass.getOwnedProperties().get(1);

		org.eclipse.ocl.pivot.Property classProp01  = (org.eclipse.ocl.pivot.Property) eFactoryAttrInstance.create(eClass_PROPERTY);
		classProp01.setName(assocProp01.getName());
		classProp01.setType(assocProp01.getType());
		org.eclipse.ocl.pivot.Property classProp02  = (org.eclipse.ocl.pivot.Property) eFactoryAttrInstance.create(eClass_PROPERTY);		
		classProp02.setName(assocProp02.getName());
		classProp02.setType(assocProp02.getType());
		
		org.eclipse.ocl.pivot.Class class01 = (org.eclipse.ocl.pivot.Class) assocCS.getAssocEnds().get(1).getOwnedType().getPivot();
		org.eclipse.ocl.pivot.Class class02 = (org.eclipse.ocl.pivot.Class) assocCS.getAssocEnds().get(0).getOwnedType().getPivot();
		
		if(class01 instanceof org.eclipse.ocl.pivot.CollectionType) {
			class01 = (org.eclipse.ocl.pivot.Class) ( (org.eclipse.ocl.pivot.CollectionType) class01 ).getElementType();
		}

		if(class02 instanceof org.eclipse.ocl.pivot.CollectionType) {
			class02 = (org.eclipse.ocl.pivot.Class) ( (org.eclipse.ocl.pivot.CollectionType) class02 ).getElementType();
		}
		
		classProp01.setOpposite(classProp02);
		classProp02.setOpposite(classProp01);
		
		boolean isAdded = true;
		for(Property prop: class01.getOwnedProperties()) {
			if(prop.getName().equals(classProp01.getName())) {
				isAdded = false;
			}
		}
		if (isAdded) {
			class01.getOwnedProperties().add(classProp01);
			class02.getOwnedProperties().add(classProp02);
		}

//		if(assocClass.getName().equals("AA")) {
//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCSPreOrderVisitor$addNewAttr4AssocClass"
//					//+ "" + + "***\n"
//					+ "\n***class01 = " + class01
//					+ "\n***class02 = " + class02
//					+ "\n***prop01 = " + classProp01
//					+ "\n***prop02 = " + classProp02
//					//+ "\n***ownedType = " + assocCS.getAssocEnds().get(0).getOwnedType().getPivot()
//					);
//			log.log(status);		
//		}
	}	
}