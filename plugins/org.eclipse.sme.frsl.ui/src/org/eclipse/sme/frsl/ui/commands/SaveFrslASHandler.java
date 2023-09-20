/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.sme.frsl.ui.commands;

import java.util.Iterator;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.URIUtil;
import org.eclipse.ocl.xtext.base.ui.commands.SaveASHandler;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.oclinecorecs.TopLevelCS;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sme.frsl.*;
import org.eclipse.sme.frsl.cs2as.FRSLCS2AS;
import org.eclipse.sme.frsl.utilities.FRSLCSResource;
import org.eclipse.sme.frslcs.FrslModelCS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

public class SaveFrslASHandler extends SaveASHandler
{
	public IStatus logMsg;
	public static ILog log = Platform.getLog(SaveFrslASHandler.class);
	
	public SaveFrslASHandler() {}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
		//logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: base.ui#SaveASHandler ****");
		//log = Platform.getLog(getClass());
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		if (window == null) {
			return null;
		}
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if (!(editor instanceof XtextEditor)) {
			return null;
		}
		//logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler ***00***" + editor.toString());
		//log.log(logMsg);
		IEditorInput editorInput = editor.getEditorInput();
		if (!(editorInput instanceof IFileEditorInput)) {
			return null;
		}
		IXtextDocument document = ((XtextEditor)editor).getDocument();
		URI asURI = null;
		try {
			asURI = document.readOnly(new IUnitOfWork<URI, XtextResource>()
			{
				@Override
				public URI exec(@Nullable XtextResource resource) throws Exception {					
//					logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler *** the resource is a FRSLCSResource: " + (resource instanceof FRSLCSResource));
//					log.log(logMsg);
					if (resource instanceof BaseCSResource) {
						Resource asResource = ((BaseCSResource)resource).getASResource();
						return asResource.getURI();
					}
					else {
						return null;
					}
				}
			});
		} catch (Exception e) {}
		if (asURI == null) {
			return null;
		}
		IFile srcFile = ((IFileEditorInput)editorInput).getFile();
		IProject srcProject = srcFile.getProject();
        URI projectURI = URI.createPlatformResourceURI(srcProject.getFullPath().toString() + "/", true);
        URI outURI = URIUtil.deresolve(asURI.trimFileExtension().trimFileExtension().appendFileExtension("frslas"), projectURI);
        IPath outPath = new Path(outURI.toString());
		IFile outFile = srcProject.getFile(outPath);
		Shell shell = editor.getEditorSite().getShell();
		SaveAsDialog dlg = new SaveAsDialog(shell);
		dlg.setOriginalFile(outFile);
		dlg.create();
		if (shell != null) {
			shell.setText(BaseUIMessages.SaveAS_ShellTitle);
		}
		dlg.setTitle(BaseUIMessages.SaveAS_Title);
		dlg.setMessage(NLS.bind(BaseUIMessages.SaveAS_Description, asURI));
		int status = dlg.open();
		if (status != SaveAsDialog.OK) {
			return null;
		}
		final IPath file = dlg.getResult();
		if (file == null) {
			return null;
		}
        final URI newURI = URI.createPlatformResourceURI(file.toString(), true);
		try {
			document.modify(new IUnitOfWork<Object, XtextResource>() {
				@Override
				public Object exec(@Nullable XtextResource resource) throws Exception {
					if (resource instanceof FRSLCSResource) {
						ASResource pivotResource = ((FRSLCSResource)resource).getASResource();
						
//						FrslModel frslModel = null;
//						FrslModelCS frslModelCS = null;
//						for(EObject eObject:pivotResource.getContents()) {
//							if(eObject instanceof Model) {
//								TopLevelCS domainModel = (TopLevelCS) ( (FRSLCS2AS) ( (FRSLCSResource) resource ).getCS2AS() ).getCSElement( (Element) eObject );
//								frslModelCS = (FrslModelCS) domainModel.eContainer();
//							}
//						}
//						if (frslModelCS != null) {
//							frslModel = (FrslModel) ( (FRSLCSResource) resource ).getFrslElementAS( (ElementCS) frslModelCS );
//						}
//						
//						if(frslModel != null) {
//							Resource frslasResource = ( (FRSLCSResource) resource ).getFrslASResource();
									
//							Resource sourceModel, targetModel;
//							soureModel.input();//frslcs.xmi
//							ATLTrafo(sourceModel, targetModel);
//							targetModel.output();//frslas.xmi ===> statechart / activity diagram / filmstrip model / gui / ifml model
						
									
//							ResourceSet frslResourceSet = pivotResource.getResourceSet();							
//							//ResourceSet frslResourceSet = new ResourceSetImpl();
//							frslResourceSet.getPackageRegistry().put(FrslPackage.eNS_URI, FrslPackage.eINSTANCE);	
//							frslResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( "frslas", new XMIResourceFactoryImpl() );
//							URI frslasURI = URI.createPlatformResourceURI(file.toString(), true).appendFileExtension("frslas");
//							Resource frslasResource = frslResourceSet.createResource(frslasURI);
							
//							FrslFactory frslFactory = FrslFactory.eINSTANCE;
//							FrslModel frslModel01 = frslFactory.createFrslModel();							
//							Usecase usecase01 = frslFactory.createUsecase();
//							Actor actor01 = frslFactory.createActor();
//							actor01.setName("actor01");
//							usecase01.setName("uc01");
//							usecase01.setDescription("the use case 01");
//							usecase01.setPrimaryActor(actor01);
//							frslModel01.getUsecase().add(usecase01);
//							frslasResource.getContents().add(frslModel01);							
//							frslasResource.save(null);
																					
//							logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute() OK - STEP01" 
//									+ "\n**** start to save frslasResource "
//									+ "\n**** domainModel = " + frslModel.getDomainModel()
//									//+ "\n**** primaryActor = " + frslModel01.getUsecase().get(0).getPrimaryActor().getName()
//									+ "\n**** frslasURI = " + frslasURI
//									+ "\n**** frslasResource = " + frslasResource.toString()
//									);
//							log.log(logMsg);
							
//							for(Resource resourceIter: frslasResource.getResourceSet().getResources()) {
//								logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute()" 
//										+ "\n**** resourceIter = " + resourceIter								
//										);
//								log.log(logMsg);
//							}							
							//frslasResource.getContents().add(frslModel.getDomainModel());
							//frslasResource.getContents().addAll( pivotResource.getContents()  );
							//frslasResource.save(null);
							
//							logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute() OK - STEP02" 
//									//+ "\n**** frslasResource[0] = " + frslasResource.getContents().get(0).getClass()
//									+ "\n**** frslasResource.getContents().size() = " + frslasResource.getContents().size()
//									+ "\n**** pivotResource.getContents().size() = " + pivotResource.getContents().size()
//									+ "\n**** frslasResource = " + frslasResource
//									+ "\n**** pivot resource = " + pivotResource
//									);
//							log.log(logMsg);	
							
//							pivotResource.getContents().add(frslModel.getDomainModel());
//							pivotResource.getContents().add(frslModel);		
//							
//							
//							org.eclipse.sme.frslAll.FrslModel frslAllModel = (org.eclipse.sme.frslAll.FrslModel) frslModel.getDomainModel();
							
							
							FrslModel frslModel = (FrslModel) pivotResource.getContents().get(0);
							//ObjVar objVar = (ObjVar) ( (Usecase)frslModel.getUsecase().get(0) ).getPrecondition().getSnapshot().getObject().get(0);
							
							//org.eclipse.ocl.pivot.Class classA = (org.eclipse.ocl.pivot.Class) frslModel.getOwnedPackages().get(1).getOwnedClasses().get(0);
							//org.eclipse.ocl.pivot.Property attr = (org.eclipse.ocl.pivot.Property) classA.getOwnedProperties().get(0);
													
							//Orphanage orphanage = Orphanage.getOrphanage(pivotResource.getResourceSet());								
							
							//orphanage.setURI(null);
							//orphanage.setName("DangDucHanh");
							//pivotResource.

							//ResourceSet myResourceSet = pivotResource.getResourceSet();							
//							myResourceSet.getResources().clear();
//							myResourceSet.getResources().add(pivotResource);
							
//							//objVar.setType( ( (org.eclipse.ocl.pivot.CollectionType) orphanage.getOwnedClasses().get(0)));
							
							//TemplateBinding attrBinding = ( (org.eclipse.ocl.pivot.CollectionType) attr.getType() ).getOwnedBindings().get(0);
							
							Iterator<Resource> resourceIter = pivotResource.getResourceSet().getResources().iterator();
							while(resourceIter.hasNext()) {
								Resource curResource = (Resource) resourceIter.next();
								logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute() STEP03" 
										//									+ "\n**** frslasResource[0] = " + frslasResource.getContents().get(0).getClass()
										//									+ "\n**** pivotResource[0] = " + pivotResource.getContents().get(0).getClass()
										//									+ "\n**** frslasResource.getContents().size() = " + frslasResource.getContents().size()
										//									+ "\n**** pivotResource.getContents().size() = " + pivotResource.getContents().size()
										//									+ "\n**** frslasResource = " + frslasResource
										//									+ "\n**** pivot resource = " + pivotResource
										//									+ "\n**** orphanage.getURI() = " + orphanage.getURI()
										//									+ "\n**** orphanage.classSize = " + orphanage.getOwnedClasses().size()
										+ "\n**** curResource = " + curResource.getURI()
										+ "\n**** packageNumber = " + curResource.getContents().size()							
										);
								//log.log(logMsg);
							}
														
//							logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute() STEP04" 
//									+ "\n**** resourceSet.size = " + pivotResource.getResourceSet().getResources().size()
//									+ "\n**** orphanage.uri = " + orphanage.getURI()
//									+ "\n**** orphanage.eContainer = " + orphanage.eContainer()
//									+ "\n**** pivotResource.packageNum = " + pivotResource.getContents().get(0).eContents().size()
//									);
//							log.log(logMsg);
														
//							for(EObject curObj: pivotResource.getContents().get(0).eContents()) {
//								logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute() STEP***" 
//										+ "\n**** curObj = " + curObj
//										);
//								log.log(logMsg);
//							}
							
							//frslModel.getUsecase().clear();
										
							//frslModel.getOwnedPackages().add(orphanage);							
							//frslModel.getOwnedPackages().add((Package) tmp0);
							//frslModel.getOwnedPackages().add((Package) tmp1);
//////							pivotResource.save(null);
//							frslModel.eContents().add(tmp2);
//							frslModel.eContents().add(tmp3);
							
							for(EObject curObj: frslModel.eContents() ) {
								logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute() STEP***" 
										+ "\n**** curObj = " + curObj
										);
								//log.log(logMsg);
							}
							
							
							
							//frslModel.eContents().remove(orphanage);
							//frslModel.getOwnedPackages().remove(orphanage);
							
							//frslModel.getOwnedPackages().add(orphanage);
							//frslModel.eContents().add(orphanage);
							
//							Iterator<org.eclipse.ocl.pivot.Package> packageIter = ((Model) pivotResource.getContents().get(0) ).getOwnedPackages().iterator();
//							while(packageIter.hasNext()) {
//								org.eclipse.ocl.pivot.Package curPackage = (org.eclipse.ocl.pivot.Package) packageIter.next();
//								logMsg = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: SaveFrslASHandler$execute() STEP05" 
//										+ "\n**** curPackage.name = " + curPackage.getName()
//										+ "\n**** curPackage.uri = " + curPackage.getURI()			
//										+ "\n**** curPackage.getOwnedPackages = " + curPackage.getOwnedPackages().size()
//										);
//								log.log(logMsg);
//								curPackage.setURI(null);
//							}

							//hanhdd - create the .oclas file (the domain class model part)
							URI oldURI = pivotResource.getURI();
							boolean wasSaveable = pivotResource.isSaveable();
							try {
								pivotResource.setURI(newURI);
								pivotResource.setSaveable(true);
								pivotResource.save(null);
							} finally {
								pivotResource.setURI(oldURI);
								pivotResource.setSaveable(wasSaveable);
							}						
					}
					return null;
				}
			});
		} catch (Throwable e) {
			MessageDialog.openError( shell, BaseUIMessages.SaveError_Title,
					e.getLocalizedMessage());
		}
		return null;
	}
}