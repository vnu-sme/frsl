/*******************************************************************************
 * Copyright (c) 2021 the VNU-SME.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     The VNU-SME
 *******************************************************************************/
package org.eclipse.sme.frsl.ui.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.sme.frsl.ui.model.FRSLDocumentProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.xtext.ui.editor.XtextEditor;

public class AbstractSaveAsHandler extends AbstractHandler
{
	protected final String persistAs;

	public AbstractSaveAsHandler(String persistAs) {
		this.persistAs = persistAs;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		if (window == null) {
			return null;
		}
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if (!(editor instanceof XtextEditor)) {
			return null;
		}
		IDocumentProvider documentProvider = ((XtextEditor)editor).getDocumentProvider();
		if (!(documentProvider instanceof FRSLDocumentProvider)) {
			return null;
		}
		((FRSLDocumentProvider)documentProvider).setPersistAs(editor.getEditorInput(), persistAs);
		editor.doSaveAs();
		return null;
	}

}