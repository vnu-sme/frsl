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
package org.eclipse.sme.frsl.ui.outline;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.base.ui.outline.BaseOutlineTreeProvider;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.essentialocl.ui.outline.EssentialOCLOutlineTreeProvider;
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS;
import org.eclipse.sme.frslcs.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;

/**
 * customization of the default outline structure
 *
 */
public class FRSLOutlineTreeProvider extends EssentialOCLOutlineTreeProvider {
	private ILog log = Platform.getLog(FRSLOutlineTreeProvider.class);
	private Status status;

	/////////////////////////////////////////////////////////////////////////////
	/////////////////////// FRSLOutlineTreeProvider /////////////////////////////
	/////////////////////////////////////////////////////////////////////////////

	/*
	 * to check if the modelElement is a meta-class of the frsl metamodel
	 */
	public boolean isFrslElement(EObject modelElement) {
		boolean ret;
		
		if( (modelElement instanceof FrslModelCS) 
				|| (modelElement instanceof UsecaseCS) 
				|| (modelElement instanceof ExtendCS) 
				|| (modelElement instanceof UsecasePreconditionCS) || (modelElement instanceof UsecasePostconditionCS) 
				|| (modelElement instanceof ActorCS) || (modelElement instanceof StepCS) || (modelElement instanceof ExtensionPointCS)
				|| (modelElement instanceof SnapshotPatternCS) 
				|| (modelElement instanceof ActStepCS) || (modelElement instanceof RejoinStepCS) || (modelElement instanceof UCStepCS)
				|| (modelElement instanceof AltFlowCS) || (modelElement instanceof SystemActionCS) || (modelElement instanceof ActorActionCS)
				|| (modelElement instanceof VarLinkCS)
				|| (modelElement instanceof ConstraintCS) 
				|| (modelElement instanceof ObjVarCS) 
				|| (modelElement instanceof AssociationCS) 
				){
			ret = true;
		}else if ( (modelElement instanceof Pivotable) && (modelElement instanceof ModelElementCS)) {
			Pivotable pivotable = (Pivotable) modelElement;
			ret = (pivotable.getPivot() == null);
		}else {
			ret = false;
		}
		return ret;
	}

//	protected void _createChildren(IOutlineNode parent, FrslModelCS csElement) {
//		createNode(parent, ((FrslModelCS)csElement).getDomainModel());
//		for(Usecase ucElement:((FrslModelCS)csElement).getUsecases()){
//			if(ucElement != null) {
//				createNode(parent, (EObject) ucElement);
//			}
//		}
//		for(Extend extElement:((FrslModelCS)csElement).getUcExtend()){
//			if(extElement != null) {
//				createNode(parent, (EObject) extElement);
//			}
//		}
//	}
	
	protected void _createChildren(IOutlineNode parent, TopLevelCS csElement) {
		super._createChildren(parent, csElement);
		FrslModelCS frslModelCS = (FrslModelCS) csElement.eContainer();
		
		org.eclipse.ocl.pivot.Package usecasePackage = (org.eclipse.ocl.pivot.Package) frslModelCS.getPivot();
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLOutlineTreeProvider$_createChildren(IOutlineNode parent, TopLevelCS csElement)" 
//				+ "\n***" + usecasePackage.getOwnedClasses().get(0).eContainer()
//				);
//		log.log(status);
	
//		for(UsecaseCS ucElementCS:frslModelCS.getUsecases()){
//			if(ucElementCS != null) {
//				_createNode(parent, (EObject) ucElementCS);
//			}
//		}
//		for(ExtendCS extElementCS:frslModelCS.getUcExtends()){
//			if(extElementCS != null) {
//				_createNode(parent, (EObject) extElementCS);
//			}
//		}
	}
	
//	protected void createChildren(IOutlineNode parent, org.eclipse.ocl.pivot.Package usecasePackage) {		
//		//_createChildren(parent, (EObject) ucElement);
//	}

//	protected void _createChildren(IOutlineNode parent, UsecaseCS ucElement) {		
//		//_createChildren(parent, (EObject) ucElement);
//	}
	
//	protected void _createChildren(IOutlineNode parent, SnapshotPatternCS ucElement) {		
//		//_createChildren(parent, (EObject) ucElement);
//	}
	
//	/////////////////////////////////////////////////////////////////////////////
//	/////////////////////// BaseOutlineTreeProvider /////////////////////////////
//	/////////////////////////////////////////////////////////////////////////////

	/**
	 * In the absence of a declarative override, creation of an outline node for a CS element
	 * is redirected to its AS counterpart.
	 */
	@Override
	protected void _createNode(DocumentRootNode parentNode, ModelElementCS csElement) {

//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLOutlineTreeProvider$_createNode(DocumentRootNode, ModelElementCS)****" + csElement.getClass());
//		log.log(status);
		
		if(csElement instanceof FrslModelCS) {			
//			Object text = textDispatcher.invoke(csElement);
//			if (text == null) {
//				text = csElement.eResource().getURI().trimFileExtension().lastSegment();
//			}
//			createEObjectNode(parentNode, csElement, imageDispatcher.invoke(csElement), text,
//					isLeafDispatcher.invoke(csElement));
			
			createNode(parentNode, ((FrslModelCS)csElement).getDomainModel().getPivot());
		}else {
			createNode(parentNode, csElement.getPivot());		
		}
	}

//	@Override
//	public void createChildren(IOutlineNode parent, EObject modelElement) {
//		
//		ILog log = Platform.getLog(BaseOutlineTreeProvider.class);
//		Status status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: BaseOutlineTreeProvider$createChildren" 
//				+ "\n****modelElement = " + modelElement
//				+ "\n****modelElement.getClass = " + modelElement.getClass()
//				);
//		log.log(status);
//		
//		
//		
//		
//		if (modelElement != null) {
//			super.createChildren(parent, modelElement);
//		}
//	}
	
//	@Override
//	protected void createNode(IOutlineNode parent, EObject modelElement) {		
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLOutlineTreeProvider$createNode(IOutlineNode, EObjet)****" + modelElement);
////		log.log(status);
//
//		if (modelElement != null) {
//			super.createNode(parent, modelElement);
//		}
//	}

	
	/**
	 * In the absence of a declarative override, creation of an outline node for a CS element
	 * is redirected to its AS counterpart.
	 */
	@Override
	protected void _createNode(IOutlineNode parent, ModelElementCS csElement) {
		if(isFrslElement(csElement)) {					
			Object text = textDispatcher.invoke(csElement);
			boolean isLeaf = isLeafDispatcher.invoke(csElement);
			if (text == null && isLeaf)
				return;
			Image image = imageDispatcher.invoke(csElement);
			createEObjectNode(parent, csElement, image, text, isLeaf);
		}else {
			createNode(parent, csElement.getPivot());
		}
	}
}