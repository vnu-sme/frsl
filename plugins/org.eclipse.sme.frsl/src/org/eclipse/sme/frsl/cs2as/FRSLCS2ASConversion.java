/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.sme.frsl.cs2as;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.AssociationClass;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.utilities.IllegalLibraryException;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.MorePivotable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.base.cs2as.BasicContinuation;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion;
import org.eclipse.ocl.xtext.base.cs2as.Continuation;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.CSI2ASMapping;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.PivotableElementCS;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.sme.frsl.*;
import org.eclipse.sme.frsl.utilities.FRSLUtil;
import org.eclipse.sme.frslcs.*;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;

public class FRSLCS2ASConversion extends CS2ASConversion{
	
	private ILog log = Platform.getLog(FRSLCS2ASConversion.class);
	private Status status;
	
	protected final @NonNull FRSLCS2AS frslConverter;
	
	public FRSLCS2ASConversion(@NonNull FRSLCS2AS converter, @NonNull IDiagnosticConsumer diagnosticsConsumer) {
		super(converter, diagnosticsConsumer);
		frslConverter = converter;
	}
		
//	@Override
//	public boolean update(@NonNull BaseCSResource csResource) {
//		
//		CSI2ASMapping csi2asMapping = frslConverter.getCsi2asMapping();
//		
//		resetPivotMappings(csResource);
//		oldPackagesByName = new HashMap<String, org.eclipse.ocl.pivot.Package>();
//		oldPackagesByQualifiedName = new HashMap<String, org.eclipse.ocl.pivot.Package>();
//		ASResource asResource = csi2asMapping.getASResource(csResource);
//		if (asResource != null) {
//			for (EObject eObject : asResource.getContents()) {
//				if (eObject instanceof Model) {
//					List<org.eclipse.ocl.pivot.Package> nestedPackage = ((Model)eObject).getOwnedPackages();
//					gatherOldPackages(nestedPackage);
//				}
//			}
//		}
//		List<BasicContinuation<?>> continuations = new ArrayList<BasicContinuation<?>>();
//		//
//		//	Perform the post-order containment traversal to:
//		//
//		//	Create the Piviotable.pivot elements for all 1:1 CS to pivot relationships.
//		//	Create the parent-child containment hierarchy.
//		//	Configure derived CS properties such as PathNameCS.elementType
//		//	Queue continuations to compute simple references
//		//
//		//	The containment pass may only access the pivot elements of immediate children.
//		//
//		
//		for (EObject eObject : csResource.getContents()) {	
//
////			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update csResource.getContents()[0] = ****" + eObject);
////			log.log(status);
//			
//			if (eObject instanceof ElementCS) {
//				visitContainment((ElementCS)eObject, continuations);
//			}
//		}	
//		
////		ASResource asResource1 = csi2asMapping.getASResource(csResource);			
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update asResource0 SIZE = ****" + (asResource1==null?0:asResource1.getContents().size()));
////		log.log(status);
//		
//		//
//		//	Put all orphan root pivot elements in their resources.
//		//
//		installRootContents(csResource);
//		//
//		//
//		//
////		ASResource asResource1 = csi2asMapping.getASResource(csResource);			
////		asResource1 = csi2asMapping.getASResource(csResource);			
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update asResource1 SIZE = ****" + (asResource1==null?0:asResource1.getContents().size()));
////		log.log(status);
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update asResource[0] = ****" + asResource.getContents().get(0));
////		log.log(status);
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update asResource[1] = ****" + asResource.getContents().get(1));
////		log.log(status);
////		
//		
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update***Step1");
////		log.log(status);
//		
//		while (continuations.size() > 0) {
//			List<BasicContinuation<?>> moreContinuations = progressContinuations(continuations);
//			if (moreContinuations == null) {
//				boolean hasNoErrors = checkForNoErrors(csResource);
//				if (!hasNoErrors) {
//					return false;
//				}
//				diagnoseContinuationFailure(continuations);
//				break;
//			}
//			continuations = moreContinuations;
//		}
//		//
//		//	Perform the pre-order traversal to resolve specializations and references.
//		//
//		for (EObject eObject : csResource.getContents()) {
//			if (eObject instanceof ElementCS) {
//				visitInPreOrder((ElementCS)eObject, continuations);	
//			}
//		}
//		
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update***Step2");
////		log.log(status);
//
//		
//		//
//		//	Perform pre-order continuations to establish package, class containment and classifier template signatures.
//		//
//		//		Collections.reverse(continuations);
//		while (continuations.size() > 0) {
//			List<BasicContinuation<?>> moreContinuations = progressContinuations(continuations);
//			if (moreContinuations == null) {
//				boolean hasNoErrors = checkForNoErrors(csResource);
//				if (!hasNoErrors) {					
////					ILog log = Platform.getLog(CS2ASConversion.class);
////					Status status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: CS2ASConversion$update hasNoErrors = ****" + hasNoErrors);
////					log.log(status);
//					return false;
//				}
//				diagnoseContinuationFailure(continuations);
//				break;
//			}
//			continuations = moreContinuations;
//		}
//		
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update***Step3");
////		log.log(status);
//		
//		//
//		//	Load the library.
//		//
//		@SuppressWarnings("unused")
//		AnyType oclAnyType = metamodelManager.getStandardLibrary().getOclAnyType();
//		//
//		//	Perform the post-order traversal to create and install the bulk of non-package/class
//		//	elements.
//		//
//		for (EObject eObject : csResource.getContents()) {
//			if (eObject instanceof ElementCS) {
////				ILog log = Platform.getLog(CS2ASConversion.class);
////				Status status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: CS2ASConversion$update post-orderTraversalTo = ****" + eObject);
////				log.log(status);
//				visitInPostOrder((ElementCS)eObject, continuations);
//			}
//		}
//		boolean hasNoErrors = checkForNoErrors(csResource);
//		if (!hasNoErrors) {
//			return false;
//		}
//		//
//		//	Perform post-order continuations to establish complex dependencies.
//		//
//		while (continuations.size() > 0) {
//			List<BasicContinuation<?>> moreContinuations = progressContinuations(continuations);
//			if (moreContinuations == null) {
//				diagnoseContinuationFailure(continuations);
//				break;
//			}
//			continuations = moreContinuations;
//		}
//		
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update***Step4");
////		log.log(status);
//		
////		for (EObject eObject : csResource.getContents()) {
////			if(eObject instanceof FrslModelCS) {
////				ModelElementCS domainModel = ((FrslModelCS) eObject).getDomainModel();
////				if(domainModel != null) {					
////					FrslModel frslModel = (FrslModel) frslCS2ASMap.get( (FrslModelCS) eObject);
////				
////					status = new Status( IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update frslModel00 = ****" + frslModel 
////							 + "\n*** domainModule = " + frslModel.getDomainModel() );
////					log.log(status);
////					
////				}
////			}
////		}
//		
//		
//		//
//		//	Put all orphan root pivot elements in their resources.
//		//
//		installRootContents(csResource);		// FIXME ExpressionInOCL very late
//		//converter.installRootContents(csResource);		// FIXME Bug 548500 workaround
//		//
//
////		for (EObject eObject : csResource.getContents()) {
////			if(eObject instanceof FrslModelCS) {
////				ModelElementCS domainModel = ((FrslModelCS) eObject).getDomainModel();
////				if(domainModel != null) {					
////					FrslModel frslModel = (FrslModel) frslCS2ASMap.get( (FrslModelCS) eObject);
////				
////					status = new Status( IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update frslModel01 = ****" + frslModel 
////							 + "\n*** domainModule = " + frslModel.getDomainModel() );
////					log.log(status);
////					
////				}
////			}
////		}
//		
//		
//		
//		boolean hasNoMoreErrors = checkForNoErrors(csResource);
//		if (!hasNoMoreErrors) {
//			return false;
//		}		//
//		
//		
//		
//		
//		//	Prune obsolete packages
//		//
//		Set<org.eclipse.ocl.pivot.Package> newPackages = new HashSet<org.eclipse.ocl.pivot.Package>();
//		gatherNewPackages(newPackages, csResource);
//		Set<org.eclipse.ocl.pivot.Package> obsoletePackages = new HashSet<org.eclipse.ocl.pivot.Package>(oldPackagesByQualifiedName.values());
//		//		for (org.eclipse.ocl.pivot.Package oldPackage : obsoletePackages) {
//		//			System.out.println("Old package @" + Integer.toHexString(oldPackage.hashCode()) + " " + oldPackage.eResource().getURI() + " " + oldPackage.getName());
//		//		}
//		//		for (org.eclipse.ocl.pivot.Package newPackage : newPackages) {
//		//			System.out.println("New package @" + Integer.toHexString(newPackage.hashCode()) + " " + newPackage.eResource().getURI() + " " + newPackage.getName());
//		//		}
//		obsoletePackages.removeAll(newPackages);
//		for (org.eclipse.ocl.pivot.Package obsoletePackage : obsoletePackages) {
//			EObject eContainer = obsoletePackage.eContainer();
//			if (eContainer != null) {
//				EReference eContainmentFeature = obsoletePackage.eContainmentFeature();
//				if (eContainmentFeature.isMany()) {
//					List<?> siblings = (List<?>) eContainer.eGet(eContainmentFeature);
//					//					System.out.println("Kill package @" + Integer.toHexString(obsoletePackage.hashCode()) + " " + obsoletePackage.eResource().getURI() + " " + obsoletePackage.getName());
//					siblings.remove(obsoletePackage);
//				}
//				else {
//					eContainer.eSet(eContainmentFeature, null);
//				}
//			}
//		}
//		
////		asResource1 = csi2asMapping.getASResource(csResource);
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update frslModel = ****" + csi2asMapping.getCSElement((Model)asResource.getContents().get(0)).eContainer().getClass());
////		log.log(status);
//
//		
////		asResource1 = csi2asMapping.getASResource(csResource);			
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update asResource1 SIZE = ****" + (asResource1==null?0:asResource1.getContents().size()));
////		log.log(status);
////		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update asResource[0] = ****" + asResource.getContents().get(0));
////		log.log(status);
//		
////		for (EObject eObject : csResource.getContents()) {
////			if(eObject instanceof FrslModelCS) {
////				ModelElementCS domainModel = ((FrslModelCS) eObject).getDomainModel();
////				if(domainModel != null) {					
////					FrslModel frslModel = (FrslModel) frslCS2ASMap.get( (FrslModelCS) eObject);
////				
////					status = new Status( IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$update frslModel02 = ****" + frslModel 
////							 + "\n*** domainModule = " + frslModel.getDomainModel() );
////					log.log(status);
////					
////				}
////			}
////		}
//
//
//		return true;
//	}
	
	@Override
	protected void installRootContents(@NonNull BaseCSResource csResource) {
		Resource asResource;
		FrslModelCS frslModelCS;
		FrslModel frslModel;
		for (EObject eObject : csResource.getContents()) {
			if(eObject instanceof FrslModelCS) {
				frslModelCS = (FrslModelCS) eObject;
				ModelElementCS domainModelCS = frslModelCS.getDomainModel();
								
				if(domainModelCS != null) {		
					@NonNull EClass eClass_PROPERTY = PivotPackage.Literals.PROPERTY;
					EFactory eFactoryAttrInstance = eClass_PROPERTY.getEPackage().getEFactoryInstance();
					@NonNull EClass eClass_CLASS = PivotPackage.Literals.CLASS;
					EFactory eFactoryClassInstance = eClass_CLASS.getEPackage().getEFactoryInstance();
					@SuppressWarnings("null") @NonNull EClass eClass_ASSOC_CLASS = PivotPackage.Literals.ASSOCIATION_CLASS;
					EFactory eFactoryAssocClassInstance = eClass_ASSOC_CLASS.getEPackage().getEFactoryInstance();
					
					Model domainModel = (Model) domainModelCS.getPivot();					
					frslModel = (FrslModel) domainModel;	
					if( domainModel == null) {
						return;
					}
					
					org.eclipse.ocl.pivot.Package usecasePackage  = (org.eclipse.ocl.pivot.Package) frslModelCS.getPivot();
					
					if( domainModel.getOwnedPackages().contains(usecasePackage)  ) {
						org.eclipse.ocl.pivot.Class  domainClass = (Class) this.frslConverter.getPivot("Class::_DomainClass");
						org.eclipse.ocl.pivot.Class  tracksClass = (Class) this.frslConverter.getPivot("Class::_Tracks");

						for(org.eclipse.ocl.pivot.Class curClass: domainModel.getOwnedPackages().get(0).getOwnedClasses() ) {
							if ( !curClass.equals( domainClass ) && !curClass.equals( tracksClass ) ) {						
								if( curClass.getSuperClasses().isEmpty() || curClass.getSuperClasses().contains(this.standardLibrary.getOclElementType() ) ) {
									curClass.getSuperClasses().add(domainClass);
									curClass.getSuperClasses().remove(this.standardLibrary.getOclElementType());
								}
							}
						}
					} else {
						domainModel.getOwnedPackages().add(usecasePackage);
						//creating the DomainClass
						org.eclipse.ocl.pivot.Class domainClass  = (org.eclipse.ocl.pivot.Class) eFactoryClassInstance.create(eClass_CLASS);
						domainClass.setName("_DomainClass");
						domainClass.getSuperClasses().add(standardLibrary.getOclElementType() );						
						org.eclipse.ocl.pivot.Property theIsProblDomAttr  = (org.eclipse.ocl.pivot.Property) eFactoryAttrInstance.create(eClass_PROPERTY);
						theIsProblDomAttr.setName("isProblDom");
						theIsProblDomAttr.setType( this.getStandardLibrary().getBooleanType() );
						domainClass.getOwnedProperties().add(theIsProblDomAttr);
						
						org.eclipse.ocl.pivot.Property problObjAttr  = (org.eclipse.ocl.pivot.Property) eFactoryAttrInstance.create(eClass_PROPERTY);
						problObjAttr.setName("problObj");
						problObjAttr.setType(domainClass);
						
						org.eclipse.ocl.pivot.Property swObjAttr  = (org.eclipse.ocl.pivot.Property) eFactoryAttrInstance.create(eClass_PROPERTY);
						swObjAttr.setName("swObj");
						swObjAttr.setType(domainClass);						
						
						org.eclipse.ocl.pivot.AssociationClass tracksAssocClass  = (org.eclipse.ocl.pivot.AssociationClass) eFactoryAssocClassInstance.create(eClass_ASSOC_CLASS);
						tracksAssocClass.setName("_Tracks");
						tracksAssocClass.getSuperClasses().add(standardLibrary.getOclElementType() );						
						tracksAssocClass.getOwnedProperties().add(problObjAttr);
						tracksAssocClass.getOwnedProperties().add(swObjAttr);	
						
//						status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$installRootContents" 
//								+ "\n*** adding rootClassSW" );
//						log.log(status);		
													
						domainModel.getOwnedPackages().get(0).getOwnedClasses().add(domainClass);
						domainModel.getOwnedPackages().get(0).getOwnedClasses().add(tracksAssocClass);
						this.frslConverter.frslCS2Pivot.put("Class::_Tracks", tracksAssocClass);
						this.frslConverter.frslCS2Pivot.put("Class::_DomainClass", domainClass);
					} 
					
														
//					status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$installRootContents frslModel = ****" + frslModel 
//								+ "\n*** domainModel.allClass.size = " + domainModel.getOwnedPackages().get(0).getOwnedClasses().size() );
//					log.log(status);			
					
					asResource = domainModel.eResource();
									
					if (asResource == null) {
						installRootElement(csResource, domainModel);						
//						for(AssociationCS assocClassCS : frslModelCS.getAssocs() ) {
//							for(ReferenceCS propertyCS : assocClassCS.getAssocEnds()) {
//								PathNameCS pathName = ( (TypedTypeRefCS) propertyCS.getOwnedType() ).getOwnedPathName();
//								FRSLUtil.refreshPathName( pathName, (@NonNull ASResource) domainModel.eResource() );
//							}
//						}
						
						for(TypedTypeRefCS typedTypeRefCS : frslConverter.allTypedTypeRefCS ) {
							
							PathNameCS pathName = typedTypeRefCS.getOwnedPathName();
								
							FRSLUtil.refreshPathName( pathName, (@NonNull ASResource) domainModel.eResource() );
							
//							status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$installRootContents" 
//							+ "\n****pathName.type=" +  typedTypeRefCS.getReferredType()
//									);
//							log.log(status);	
							
						}
						
						//frslConverter.getFrslASResource().getContents().add(frslModel);
				
					} 
				}
			} else if (eObject instanceof Pivotable) {
				Element pivotElement = ((Pivotable)eObject).getPivot();
				if (pivotElement != null) {
					asResource = pivotElement.eResource();
					if (asResource == null) {
						installRootElement(csResource, pivotElement);
					}
				}
			}			
			
		}
	}
	

	public void refreshFrslElement(@NonNull ElementCS csElement, @NonNull EObject newEObjectElement) {
		frslConverter.frslCS2ASMap.put(csElement, newEObjectElement);
		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$refreshFrslElement"
//				+ "\ncsElement = " + csElement.getClass()
//				+ "\nfrslCS2ASMap SIZE = " + frslCS2ASMap.size()
//				+ "\ncsElement.hashcode = " + csElement.hashCode()
//				+ "\nasElement.hashcode = " + newEObjectElement.hashCode()
//				);
//		log.log(status);

	}
	
	public ElementCS lookupFrslElementCS(@NonNull String className, @NonNull PathNameCS csPathName) {
		String csPathElementName = "";
		if ( csPathName.getOwnedPathElements().size() > 0 ) {
			csPathElementName = csPathName.getOwnedPathElements().get( csPathName.getOwnedPathElements().size() - 1 ).toString();
		}
		return lookupFrslElementCS(className, csPathElementName);
		
	}

	public EObject lookupFrslElementAS(@NonNull String className, @NonNull PathNameCS csPathName) {
		ElementCS elementCS = lookupFrslElementCS(className, csPathName);
		return frslConverter.frslCS2ASMap.get(elementCS); 
	}
	
	public ElementCS lookupFrslElementCS(@NonNull String className, @NonNull String objName) {
		for(ElementCS elementCS : frslConverter.frslCS2ASMap.keySet()) {
			
//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$lookupFrslElementCS"
//					+ "\n elementCS.eClass.getName = " + elementCS.eClass().getName()
//					//+ "\n elementCS.getCsi() = " + elementCS.getCsi()
//					//+ "\n elementCS.getDescription = " + elementCS.getDescription()
//					+ "\n elementCS.toString = " + elementCS.toString()
//					//+ "\n csPathName.toString() = " + csPathName.toString() 
//					+ "\n csPathElementName = " + "(name: " + csPathElementName + ")" 
//					);
//			log.log(status);
			
			// if( className.equals( elementCS.getDescription() ) && elementCS.toString().endsWith( "(name: " + csPathName.toString() + ")" ) ){			
			if( elementCS.eClass().getName().equals( className ) && 
					elementCS.toString().contains( "(name: " + objName + ",") 
					|| elementCS.toString().contains( "(name: " + objName + ")") ) {	
//					( elementCS.toString().endsWith( "(name: " + objName + ")" ) 
//							|| elementCS.toString().endsWith( "(name: " + objName + ", description: null)" ) ) ) {				
				return elementCS;
			}
		}
		return null;
	}
	
	public EObject lookupFrslElementAS(@NonNull String className, @NonNull String objName) {
		ElementCS elementCS = lookupFrslElementCS(className, objName);
		return frslConverter.frslCS2ASMap.get(elementCS); 
	}
	
	public StepCS lookupStepCS(UsecaseCS usecaseCS, String stepName) {
		for(ElementCS elementCS : frslConverter.frslCS2ASMap.keySet()) {
			
//			status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$lookupStepCS"
//					+ "\n elementCS.eClass.getName = " + elementCS.eClass().getName()
//					+ "\n elementCS.toString = " + elementCS.toString()
//					+ "\n stepName = " + "(name: " + stepName + ")" 
//					);
//			log.log(status);

			if( ( ( elementCS.eClass().getName().equals("ActStepCS") ) || (elementCS.eClass().getName().equals( "UCStepCS" )) ) 
					&& ( elementCS.toString().contains( "(name: " + stepName + ",") 
							|| elementCS.toString().contains( "(name: " + stepName + ")") ) ) {
	
//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$lookupStepCS"
//						+ "\n elementCS.eClass.getName = " + elementCS.eClass().getName()
//						+ "\n elementCS.toString = " + elementCS.toString()
//						+ "\n stepName = " + "(name: " + stepName + ")" 
//						+ "\n usecase = " + FRSLUtil.getUsecaseCS(this, (StepCS) elementCS).getName()
//						+ "\n usecaseCS = " + usecaseCS.getName()
//						);
//				log.log(status);
				
				if( FRSLUtil.getUsecaseCS(this, (StepCS) elementCS).equals(usecaseCS)) {
					return (StepCS) elementCS;
				}
			}
		}
		return null;
	}	
	
	public ExtensionPointCS lookupExtPointCS(UsecaseCS extendedUsecaseCS, ModelElementRefCS extPointCSRef) {
		if( extPointCSRef.getOwnedPathName().getOwnedPathElements().size() != 1) {
			return null;
		}
		String extPointCSName = extPointCSRef.getOwnedPathName().getOwnedPathElements().get(0).toString();
		for(ElementCS elementCS : frslConverter.frslCS2ASMap.keySet()) {
			if( ( elementCS.eClass().getName().equals("ExtensionPointCS") )  
					&& ( elementCS.toString().contains( "(name: " + extPointCSName + ",") 
							|| elementCS.toString().contains( "(name: " + extPointCSName + ")") ) ) {

				if( ( (UsecaseCS) elementCS.eContainer() ).equals(extendedUsecaseCS) ) {
					return (ExtensionPointCS) elementCS;
				}
			}
		}
		return null;
	}
	
	public EObject lookupObjVarAS(SnapshotPatternCS snapshotPatternCS, ModelElementRefCS objVarRefCS) {
		if( objVarRefCS.getOwnedPathName().getOwnedPathElements().size() != 1) {
			return null;
		}
		String objVarNameCS = objVarRefCS.getOwnedPathName().getOwnedPathElements().get(0).toString();
		for(ElementCS elementCS : frslConverter.frslCS2ASMap.keySet()) {
			if( ( elementCS.eClass().getName().equals("ObjVarCS") )  
					&& ( elementCS.toString().contains( "(name: " + objVarNameCS + ",") 
							|| elementCS.toString().contains( "(name: " + objVarNameCS + ")") ) ) {
				if( ( (SnapshotPatternCS) elementCS.eContainer() ).equals(snapshotPatternCS) ) {
					return frslConverter.getFrslElementAS(elementCS);
				}
			}
		}
		return null;
	}

	
	public EObject getFrslElementAS(@NonNull ElementCS frslElementCS) {
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$getFrslElementAS"
//				+ "\n frslCS2ASMap SIZE = " + frslCS2ASMap.size()
//				+ "\n frslElementCS = " + frslElementCS.getDescription()
//				+ "\n frslElementCS.hashCode = " + frslElementCS.hashCode()
//				+ "\n frslElementAS.hashCode = " + ( frslCS2ASMap.get(frslElementCS) == null ? 0 : frslCS2ASMap.get(frslElementCS).hashCode() )
//				);
//		log.log(status);
		
		
		return frslConverter.getFrslElementAS(frslElementCS);
	}
	
	public ElementCS getFrslElementCS(@NonNull EObject frslElement) {			
		return frslConverter.getFrslElementCS(frslElement);
	}
	
	public Element setPivot(@NonNull String frslMetaConceptCS_ElemName, Element element) {
		return frslConverter.frslCS2Pivot.put(frslMetaConceptCS_ElemName, element);
	}
	
	public Element getPivot(@NonNull String frslMetaConceptCS_ElemName) {
		return frslConverter.getPivot(frslMetaConceptCS_ElemName);
	}
	
	public EObject getPostElement(@NonNull EObject preElement) {
		return frslConverter.frslCSPre2Post.get(preElement);
	}

//	/**
//	 * Add any packages and nested packages pivoted by csResource to newPackages. This
//	 * is invoked at the end of an update to identify redundant packages.
//	 */
//	@Override
//	protected void gatherNewPackages(@NonNull Set<org.eclipse.ocl.pivot.Package> newPackages, @NonNull Resource csResource) {
//		for (TreeIterator<EObject> tit = csResource.getAllContents(); tit.hasNext(); ) {
//			EObject eObject = tit.next();
//			if (eObject instanceof FrslModelCS){
//				ModelElementCS domainModel = ((FrslModelCS) eObject).getDomainModel();
//				if(domainModel != null) {					
//					FrslModel frslModel = (FrslModel) frslCS2ASMap.get( (FrslModelCS) eObject);
//					if( (frslModel.getDomainModel() != null) && (frslModel.getDomainModel().size() > 0) ){
//						Element pObject = frslModel.getDomainModel().get(0);
//						gatherNewPackage(newPackages, pObject);
//					}
//				}
//			}else if (eObject instanceof Pivotable) {
//				Element pObject = ((Pivotable)eObject).getPivot();
//				if (pObject instanceof org.eclipse.ocl.pivot.Package) {
//					gatherNewPackage(newPackages, pObject);
//				}
//				else if (pObject instanceof Model) {
//					gatherNewPackage(newPackages, pObject);
//				}
//				else {		// CompleteOCL has package references from non-package contexts
//					if (pObject instanceof Type) {
//						gatherNewPackage(newPackages, pObject);
//					}
//					else if (pObject instanceof Operation) {
//						gatherNewPackage(newPackages, pObject);
//					}
//					else if (pObject instanceof Property) {
//						gatherNewPackage(newPackages, pObject);
//					}
//					tit.prune();
//				}
//				if (eObject instanceof MorePivotable) {  // CompleteOCL has package references from non-package contexts
//					for (Element pivot : ((MorePivotable)eObject).getMorePivots()) {
//						if (pivot != null) {
//							gatherNewPackage(newPackages, pivot);
//						}
//					}
//				}
//			}
//		}
//	}
//
	
	
	/**
	 * Prune the pivots to eliminate:
	 * - redundant orphans - e.g. obsolete specializations
	 * - redundant elements - e.g. pivots that are not needed as a result of a CS update
	 * - dependent caches that reference any of the above
	 *
	 * Wanted pivot elements are
	 * - all elements in all libraries
	 * - all elements locked via MetamodelManager.addLockedElement()
	 * - all elements transitively in/referenced from the above
	 * - all elements referenced by incoming CS resources
	 * - all elements in all incoming pivot resources
	 * - except pivot resources mapped to incoming CS resources
	 *     this is what we're cleaning up
	 */
	@Override
	public void garbageCollect(@NonNull Map<? extends Resource, ? extends ASResource> cs2asResourceMap) {
		//		org.eclipse.ocl.pivot.Class orphanClass = metamodelManager.getOrphanClass();
		//		org.eclipse.ocl.pivot.Package orphanPackage = metamodelManager.getOrphanPackage();
		//		Resource orphanResource = orphanPackage.eResource();
		final Collection<Notifier> prunableResources = new ArrayList<Notifier>(cs2asResourceMap.values());
		//		prunableResources.add(orphanResource);
		Collection<Notifier> allPivotResources = new ArrayList<Notifier>(metamodelManager.getASResourceSet().getResources());
		//		allPivotResources.removeAll(prunableResources);					// Dead elements in orphanage or pivot of CS can be pruned
		EObject lockingObject = metamodelManager.getLockingObject();
		if (lockingObject != null) {
			allPivotResources.add(lockingObject);						// Locked elements are not dead
		}
		allPivotResources.addAll(metamodelManager.getLibraries());			// Library elements are not dead
		allPivotResources.addAll(cs2asResourceMap.keySet());			// Incoming elements are not dead
		allPivotResources.remove(metamodelManager.getCompleteModel().getOrphanage().eResource());
		
		Resource frslModelResource = null;
		for(Notifier resource: allPivotResources) {
			if( (resource instanceof Resource) && (( (Resource) resource).getContents() != null)) {

//				status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLCS2ASConversion$garbageCollect"
//						+ "myResource = " + ( (Resource) myResource).getContents().get(0).getClass()
//						);
//				log.log(status);
				
				if( ( ( (Resource) resource).getContents().size() > 0 ) && ( ( (Resource) resource).getContents().get(0) instanceof FrslModel) ){
					frslModelResource = (Resource) resource;					
				}
			}
		}
		
		allPivotResources.remove(frslModelResource);
		
		
		@SuppressWarnings("serial")
		Map<EObject, Collection<Setting>> referencesToOrphans = new EcoreUtil.CrossReferencer(allPivotResources)
		{
			{ crossReference(); }
			@Override
			protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject) {
				Resource eResource = crossReferencedEObject.eResource();
				boolean isPrunable = prunableResources.contains(eResource);
				//				if (isPrunable && prunableResources.contains(eObject.eResource())) {
				//					PivotUtil.debugObjectUsage("prunable xref ", crossReferencedEObject);
				//					PivotUtil.debugObjectUsage(" from " + eReference.getName() + " ", eObject);
				//				}
				//				else {
				//					PivotUtil.debugObjectUsage("unprunable xref ", crossReferencedEObject);
				//					PivotUtil.debugObjectUsage(" from " + eReference.getName() + " ", eObject);
				//				}
				return isPrunable;
			}

			@Override
			protected void handleCrossReference(EObject eObject) {
				try {
					super.handleCrossReference(eObject);
					InternalEObject internalEObject = (InternalEObject)eObject;
					for (EObject eContent : eObject.eContents()) {
						EReference eReference = (EReference) eContent.eContainingFeature();
						add(internalEObject, eReference, eContent);
					}
				}
				catch (Exception e) {}
			}

			@Override
			protected boolean resolve() {
				return false;		// Don't start creating specializations to resolve proxies
			}
		};
		Set<EObject> wantedOrphans = new HashSet<EObject>();
		List<Map.Entry<EObject, Collection<EStructuralFeature.Setting>>> suspects = new ArrayList<Map.Entry<EObject, Collection<EStructuralFeature.Setting>>>();
		for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : referencesToOrphans.entrySet()) {
			EObject referencedOrphan = entry.getKey();
			Collection<EStructuralFeature.Setting> referencesToOrphan = entry.getValue();
			boolean wantIt = false;
			for (EStructuralFeature.Setting setting : referencesToOrphan) {
				EObject eObject = setting.getEObject();
				Resource eResource = eObject.eResource();
				if (!prunableResources.contains(eResource)) {
					//					PivotUtil.debugObjectUsage("externally refd ", referencedOrphan);
					wantedOrphans.add(referencedOrphan);
					wantIt = true;
					break;
				}
			}
			if (!wantIt) {
				//				if ((referencedOrphan != orphanPackage) && (referencedOrphan != orphanClass)) {
				//					PivotUtil.debugObjectUsage("externally unrefd ", referencedOrphan);
				suspects.add(entry);
				//				}
				//				else {
				//					PivotUtil.debugObjectUsage("unkillable ", referencedOrphan);
				//				}
			}
		}
		while (!suspects.isEmpty()) {
			List<Map.Entry<EObject, Collection<EStructuralFeature.Setting>>> oldSuspects = suspects;
			suspects = new ArrayList<Map.Entry<EObject, Collection<EStructuralFeature.Setting>>>();
			for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : oldSuspects) {
				EObject referencedOrphan = entry.getKey();
				Collection<EStructuralFeature.Setting> referencesToOrphan = entry.getValue();
				boolean wantIt = false;
				for (EStructuralFeature.Setting setting : referencesToOrphan) {
					EObject eObject = setting.getEObject();
					if (wantedOrphans.contains(eObject)) {
						//						PivotUtil.debugObjectUsage(pass + " internally refd ", referencedOrphan);
						//						PivotUtil.debugObjectUsage("     by ", eObject);
						wantedOrphans.add(referencedOrphan);
						wantIt = true;
						break;
					}
				}
				if (!wantIt) {
					//					PivotUtil.debugObjectUsage(pass + " internally unrefd ", referencedOrphan);
					suspects.add(entry);
				}
			}
			if (oldSuspects.size() <= suspects.size()) {
				break;
			}
		}
		for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : suspects) {
			EObject referencedOrphan = entry.getKey();
			boolean wantIt = false;
			for (EObject eChild : referencedOrphan.eContents()) {		// FIXME this avoids a loss of the Ecore part of a Complete OCL resource
				if (wantedOrphans.contains(eChild)) {					//  surely an earlier containment want search should have found this
					wantIt = true;
					break;
				}
			}
			if (!wantIt) {
				//				PivotUtil.debugObjectUsage("kill ", referencedOrphan);
				Collection<EStructuralFeature.Setting> referencesToOrphan = entry.getValue();
				if (referencesToOrphan != null) {
					for (EStructuralFeature.Setting setting : referencesToOrphan) {
						EObject eObject = setting.getEObject();
						EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
						if (!eStructuralFeature.isDerived()) {
							//							PivotUtil.debugObjectUsage(" non-derived " + eStructuralFeature.getEContainingClass().getName() + "::" + eStructuralFeature.getName() + " : ", eObject);
							if (eStructuralFeature.isMany()) {
								@SuppressWarnings("unchecked")
								Collection<Object> list = (Collection<Object>) eObject.eGet(eStructuralFeature);
								list.remove(referencedOrphan);
							}
							else {
								eObject.eSet(eStructuralFeature, null);
							}
						}
						else {
							//							PivotUtil.debugObjectUsage(" derived " + eStructuralFeature.getEContainingClass().getName() + "::" + eStructuralFeature.getName() + " : ", eObject);
							//							System.out.println("  derived " + eObject.getClass().getName() + "@" + eObject.hashCode() + " " + eStructuralFeature.getName());
						}
					}
				}
				EObject eContainer = referencedOrphan.eContainer();
				if (eContainer != null) {
					PivotUtil.debugObjectUsage("  container ", eContainer);
					referencedOrphan.eSet(referencedOrphan.eContainingFeature(), null);
				}
				//WIP			metamodelManager.kill(referencedOrphan);
			}
		}
		//		for (MonikeredElement element : oldMoniker2asMap.values()) {
		//			if (element.eResource() == null) {
		//				PivotUtil.debugObjectUsage("Final Old residue ", element);
		// This is a bit late: a notification of non-containment would be sharper.
		//				metamodelManager.kill(element);
		//			}
		//		}
		//		for (MonikeredElement element : newMoniker2asMap.values()) {
		//			if (element.eResource() == null) {
		//				PivotUtil.debugObjectUsage("Final New residue ", element);
		//			}
		//		}
		//		for (Type orphanType : orphanPackage.getOwnedType()) {
		//			if (!PivotUtil.debugWellContainedness(orphanType)) {
		//				for (Setting setting : referencesToOrphans.get(orphanType)) {
		//					PivotUtil.debugObjectUsage("Dangling reference " + setting.getEStructuralFeature().getName() + " ", setting.getEObject());
		//				}
		//			}
		//		}
	}	
}