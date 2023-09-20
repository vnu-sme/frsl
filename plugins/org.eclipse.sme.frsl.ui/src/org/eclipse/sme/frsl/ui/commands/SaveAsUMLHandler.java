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
package org.eclipse.sme.frsl.ui.commands;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.m2m.atl.common.ATLExecutionException;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.frsl2uml.files.Frsl2Sm;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.URIUtil;
import org.eclipse.ocl.xtext.base.ui.messages.BaseUIMessages;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.oclinecore.ui.commands.AbstractSaveAsHandler;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sme.frsl.ui.model.FRSLDocumentProvider;
import org.eclipse.sme.frsl.utilities.FRSLCSResource;
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
import org.xml.sax.InputSource;

public class SaveAsUMLHandler extends AbstractSaveAsHandler
{
	public IStatus logMsg;
	public static ILog log = Platform.getLog(SaveAsUMLHandler.class);
	
	public SaveAsUMLHandler() {
		super(FRSLDocumentProvider.PERSIST_AS_UML);
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
//		HandlerUtil.getActiveWorkbenchWindow(event).close();
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		if (window == null) {
			return null;
		}
		
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if (!(editor instanceof XtextEditor)) {
			return null;
		}
	
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
        URI outURI = URIUtil.deresolve(asURI.trimFileExtension().appendFileExtension("uml"), projectURI);
        IPath outPath = new Path(outURI.toString());
		IFile outFile = srcProject.getFile(outPath);
		Shell shell = editor.getEditorSite().getShell();
		SaveAsDialog dlg = new SaveAsDialog(shell);
		dlg.setOriginalFile(outFile);
		dlg.create();
		if (shell != null) {
			shell.setText("Transform to UML");
		}
		dlg.setTitle("Save to State Machine and Class Diagram");
		dlg.setMessage(NLS.bind("Transforming FRSL to UML", asURI));
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
						
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						pivotResource.save(byteArrayOutputStream, null);
						byte[] buffer = byteArrayOutputStream.toByteArray();
						ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
						byteArrayOutputStream.close();
						InputSource inputSource = new InputSource(byteArrayInputStream);
						InputStream inputStream = inputSource.getByteStream();
						
//						URI oldURI = pivotResource.getURI();
//						boolean wasSaveable = pivotResource.isSaveable();
						
//						String inputPath = oldURI.path().substring(0, oldURI.path().length()-6);
//						String inputPath = oldURI.path();
						
			        	String[] inputAtl = new String[3];
			    		inputAtl[0] = "http://www.eclipse.org/ocl/2015/Library.oclas";
			    		inputAtl[1] = "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml";
			    		inputAtl[2] = "platform:" + newURI.path();
//			    		Frsl2Sm2.main(inputAtl, inputStream);
			    		this.genUml(inputAtl, inputStream);
			    		
			    		System.out.println("=====" + inputAtl[0] + "=====" + inputAtl[1]);
			    		
//						try {
//							pivotResource.setURI(newURI);
//							pivotResource.setSaveable(true);
//							pivotResource.save(null);
//						} finally {
//							pivotResource.setURI(oldURI);
//							pivotResource.setSaveable(wasSaveable);
//						}						
					}
					return null;
				}
				
				public void genUml(String[] args, InputStream inputStream) {
					try {
						if (args.length < 3) {
							System.out.println("===== Arguments not valid !!!");
						} else {
							Frsl2Sm runner = new Frsl2Sm();
							runner.loadModels(inputStream, args[0], args[1]);
							runner.doFrsl2Sm(new NullProgressMonitor());
							runner.saveModels(args[2]);
						}
					} catch (ATLCoreException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ATLExecutionException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Throwable e) {
			MessageDialog.openError(shell, BaseUIMessages.SaveError_Title, e.getLocalizedMessage());
		}
        
        return null;
	}
}
