package org.eclipse.m2m.atl.frsl2uml.files;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

public class Main implements IViewActionDelegate {

	public void run(IAction action) {
//		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		IEditorPart editor = page.getActiveEditor();
//		IEditorInput input = editor.getEditorInput();
//		IFile file = (IFile)Platform.getAdapterManager().getAdapter(input, IFile.class);
		
		//TODO: Display a message to require the textual frsl file opened before performing the transformation. 
		
		String[] inputAtl = new String[4];
		
		ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
		IStructuredSelection structured = (IStructuredSelection) service.getSelection();
		IFile file = (IFile) structured.getFirstElement();
		
		inputAtl[0] = file.getLocationURI().toString();
		inputAtl[1] = "http://www.eclipse.org/ocl/2015/Library.oclas";
		inputAtl[2] = "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml";
		String outUmlFile = file.getName().substring(0, file.getName().length() - file.getFileExtension().length()) + ".uml";
		inputAtl[3] = "platform:/resource/" + file.getProject().getName() + "/uml-gen/" + outUmlFile; 
				
		Frsl2Sm.main(inputAtl);
		
//		String[] inputAtl = new String[4];
//		BaseDocument document = (BaseDocument) ((XtextEditor)editor).getDocument();
//		try {
//			ASResource pivotResource = document.getASResource();
//			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//			pivotResource.save(byteArrayOutputStream, null);
//			byte[] buffer = byteArrayOutputStream.toByteArray();
//			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
//			byteArrayOutputStream.close();
//			InputSource inputSource = new InputSource(byteArrayInputStream);
//			InputStream inputStream = inputSource.getByteStream();
//					
//			inputAtl[0] = "http://www.eclipse.org/ocl/2015/Library.oclas";
//			inputAtl[1] = "pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml";
//			inputAtl[2] = "platform:/resource/" + file.getProject().getName() + "/uml-gen/" + file.getName() + ".uml";
//			try {
//				Frsl2Sm runner = new Frsl2Sm();
//				runner.loadModels(inputStream, inputAtl[0], inputAtl[1]);
//				runner.doFrsl2Sm(new NullProgressMonitor());
//				runner.saveModels(inputAtl[2]);
//			} catch (ATLCoreException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ATLExecutionException e) {
//				e.printStackTrace();
//			}
//			
//		} catch (CoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IViewPart view) {
		// Used for viewerContributions, not for objectContributions
	}
}