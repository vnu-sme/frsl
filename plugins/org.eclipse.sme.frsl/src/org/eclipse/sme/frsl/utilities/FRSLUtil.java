package org.eclipse.sme.frsl.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpCS;
import org.eclipse.ocl.xtext.essentialoclcs.ExpSpecificationCS;
import org.eclipse.sme.frslcs.*;
import org.eclipse.sme.frsl.*;
import org.eclipse.sme.frsl.cs2as.FRSLCS2ASConversion;

public class FRSLUtil {

	public static void refreshPathName(@NonNull PathNameCS pathNameCS, @NonNull ASResource asResource) {
		for(PathElementCS pathElementCS : pathNameCS.getOwnedPathElements()) {
			
			String typeName = pathElementCS.toString();
			if(typeName == null) {
				return;
			}
			Iterator<EObject> iter = asResource.getAllContents();
			while(iter.hasNext()) {
				EObject eObject = iter.next();
				if( (eObject instanceof org.eclipse.ocl.pivot.NamedElement) && typeName.equals( ( (org.eclipse.ocl.pivot.NamedElement) eObject ).getName()  ) ){
					pathElementCS.setReferredElement( (org.eclipse.ocl.pivot.Element) eObject);
					if(eObject instanceof org.eclipse.ocl.pivot.Class) {
						pathElementCS.setElementType(PivotPackage.Literals.TYPE);
					}
				}
			}
			
			pathElementCS.setElementType(PivotPackage.Literals.NAMESPACE);
			
//			ILog log = Platform.getLog(FRSLUtil.class);
//			Status status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLUtil$refreshPathName"
//					+ "\n***pathElementCS = " + pathElementCS 
//					+ "\n***pathElementCS.getPivot = " + pathElementCS.getPivot());
//			log.log(status);
			
		}
	}
	
	public static void installPackage(org.eclipse.ocl.pivot. @NonNull Model domainModel, org.eclipse.ocl.pivot. @NonNull AssociationClass assocClass){		
		ILog log = Platform.getLog(FRSLUtil.class);
		Status status;
		String sourceTypeName = "";
		if(assocClass.getOwnedProperties().size() > 0) {
			sourceTypeName = assocClass.getOwnedProperties().get(0).getType().toString();
		}
		String sourceClassName = sourceTypeName;
		if( (sourceTypeName.length() > 0) && ( sourceTypeName.charAt(sourceTypeName.length() - 1) == ')' ) ) {
			sourceClassName = sourceTypeName.substring( sourceTypeName.indexOf('(') + 1, sourceTypeName.length() - 1);
			if( sourceClassName.indexOf('[') > 0) {
				sourceClassName = sourceClassName.substring(0, sourceClassName.indexOf('['));
			}
			
		}
		org.eclipse.ocl.pivot.Package curPackage = null;

		//curPackage = domainModel.getOwnedPackages().get(0);
		for(org.eclipse.ocl.pivot.Package pck : domainModel.getOwnedPackages()) {
			for(org.eclipse.ocl.pivot.Class cls: pck.getOwnedClasses() ) {
//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLUtil$installPackage"
//						+ "\n cls.name = " + cls.toString()
//						);
//				log.log(status);
				if( (cls.toString().equals(sourceClassName) ) || cls.toString().equals(curPackage + "::" + sourceClassName) ) {
					curPackage = pck;
					break;
				}
			}
			if( curPackage != null) {
				break;
			}
		}
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLUtil$installPackage"
//				+ "\n***sourceTypeName = " + sourceTypeName 
//				+ "\n***sourceClassName = " + sourceClassName
//				+ "\n***curPackage = " + curPackage
//				);
//		log.log(status);
		
		if ( curPackage != null ) {
			curPackage.getOwnedClasses().add(assocClass);
			assocClass.setOwningPackage(curPackage);
		}
	}

//	public static int[] getStepNumber(@NonNull Step step) {
//		int[] ret = new int[2];
//		int stepNumber = 1;
//		Step curStep = step;
//		while( curStep.getPreStep() != null) {
//			curStep = curStep.getPreStep();
//			stepNumber ++;			
//		}
//		if( curStep.eContainer() instanceof Usecase) {
//			ret[0] = 1;
//			ret[1] = stepNumber;
//		} else if ( curStep.eContainer() instanceof AltFlow ) {
//			Step baseStep = ( (AltFlow) curStep.eContainer()).getBaseStep();
//			int[] tmp = getStepNumber(baseStep);
//			ret[0] = tmp[0] * 100 + ret[0];
//			ret[1] = 0;	
//			for(AltFlow altFlow: baseStep.getAltFlow()) {
//				ret[1]++;
//				if(altFlow.equals(curStep.eContainer() )) {
//					break;
//				}
//			}
//			ret[1] = tmp[1] * 100 + ret[1];
//		} else {
//			ret[0] = 0;
//		}
//		return ret;		
//	}
	
	public static String getStepNumberString(@NonNull Step step) {
		int stepNumber = 1;
		Step curStep = step;
		while( curStep.getPreStep() != null) {
			curStep = curStep.getPreStep();
			stepNumber ++;			
		}
		if( curStep.eContainer() instanceof Usecase) {
			return "" + stepNumber;
		} else if ( curStep.eContainer() instanceof AltFlow ) {
			Step baseStep = ( (AltFlow) curStep.eContainer()).getBaseStep();
			int altStepOrder = 0;	
			for(AltFlow altFlow: baseStep.getAltFlow()) {
				altStepOrder++;
				if(altFlow.equals(curStep.eContainer() )) {
					break;
				}
			}
			return getStepNumberString(baseStep) + Character.toString('a' + altStepOrder - 1) + stepNumber;
		} else {
			return "";
		}		
	}
	
	public static String getAtlFlowStepString(@NonNull AltFlow altFlow) {
		Step baseStep = altFlow.getBaseStep();
		int altStepOrder = 0;	
		for(AltFlow curAltFlow: baseStep.getAltFlow()) {
			altStepOrder++;
			if(curAltFlow.equals(altFlow)) {
				break;
			}
		}
		return getStepNumberString(baseStep) + Character.toString('a' + altStepOrder - 1);		
	}
	
	public static String getRejoinStepString(RejoinStep rejoinStep) {
		Step step = rejoinStep.getRejoinTo();		
		step.getRejoinFrom();
		int rejoinOrder = 0;	
		for(RejoinStep curRejoinStep: step.getRejoinFrom()) {
			rejoinOrder++;
			if(curRejoinStep.equals(rejoinStep)) {
				break;
			}
		}
		return getStepNumberString(step) + "_" + rejoinOrder;
	}

	public static UsecaseCS getUsecaseCS(@NonNull FRSLCS2ASConversion context, @NonNull StepCS stepCS) {
		ILog log = Platform.getLog(FRSLUtil.class);
		Status status;

		StepCS curStepCS = stepCS;
		while( curStepCS.eContainer() instanceof StepCS) {
			curStepCS = (StepCS) curStepCS.eContainer();
		}
		if (curStepCS.eContainer() instanceof UsecaseCS) {
			return (UsecaseCS) curStepCS.eContainer();
		}
		if (curStepCS.eContainer() instanceof AltFlowCS) {
			return getUsecaseCS(context, (StepCS) curStepCS.eContainer().eContainer());
		}
		return null;
	}

	public static void refreshOpConstraint(@NonNull CS2ASConversion context, @NonNull PivotHelper helper, @NonNull List<Constraint> opConstraint, SnapshotPatternCS snapshotCS, Boolean isPrecond) {
		if(snapshotCS == null) {
			return;
		}
		int i = 1;
		for(ConstraintCS constraintCS: snapshotCS.getConstraints() ) {
			ExpSpecificationCS oclExprCS = constraintCS.getConstrExpr();
			ExpressionInOCL asSpecification = PivotUtil.getPivot(ExpressionInOCL.class, oclExprCS);
			
			org.eclipse.ocl.pivot.Constraint constraint = (org.eclipse.ocl.pivot.Constraint) constraintCS.getPivot();
			opConstraint.add(constraint);
			constraint.setName( ( (org.eclipse.ocl.pivot.NamedElement) ( (Pivotable) snapshotCS.eContainer() ).getPivot()  ).getName() + (isPrecond?"_pre" : "_post") + i);
			i++;

			constraint.setOwnedSpecification(asSpecification);
		
			if (asSpecification != null) {
				context.refreshContextVariable(oclExprCS, asSpecification);
				ExpCS csExpression = oclExprCS.getOwnedExpression();
				OCLExpression asExpression = csExpression != null ? context.visitLeft2Right(OCLExpression.class, csExpression) : null;
				String statusText = csExpression != null ? ElementUtil.getExpressionText(csExpression) : "null";
				PivotUtil.setBody(asSpecification, asExpression, statusText);
				boolean isRequired = (asExpression != null) && asExpression.isIsRequired();
				helper.setType(asSpecification, asExpression != null ? asExpression.getType() : null, isRequired);

//				ILog log = Platform.getLog(FRSLUtil.class);
//				Status status;				
//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLUtil$refreshOpConstraint"
//						+ "\n***oclExprCS = " + oclExprCS
//						+ "\n***oclExprCS.getOwnedExpression() = " + oclExprCS.getOwnedExpression()
//						+ "\n***asSpecification = " + asSpecification
//						+ "\n***asExpression = " + asExpression
//						+ "\n***asExpression.eContainer() = " + asExpression.eContainer()
//						);
//				log.log(status);
	
			}
		}
		
	}

	public static boolean isEqualVarLink(VarLink preVarLink, VarLink postVarLink) {
		String preObj01Name = preVarLink.getObjVar().get(0).getName();
		String preObj02Name = preVarLink.getObjVar().get(1).getName();
		String postObj01Name = postVarLink.getObjVar().get(0).getName();
		String postObj02Name = postVarLink.getObjVar().get(1).getName();
		return preObj01Name.equals(postObj01Name) && preObj02Name.equals(postObj02Name);
	}
	
	
//	public static void removeFrslModelResource(PivotMetamodelManager metamodelManager) {
//		List<Resource> allPivotResources = new ArrayList<Resource>( metamodelManager.getASResourceSet().getResources() );
//
//		Resource frslModelResource = null;
//		for(Resource resource: allPivotResources) {
//			if( (resource instanceof Resource) && ((  resource).getContents() != null)) {			
//				if( ( resource).getContents().get(0) instanceof FrslModel){
//					frslModelResource = resource;					
//				}
//			}
//		}
//		
//		allPivotResources.remove(frslModelResource);
//	}
}