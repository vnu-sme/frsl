/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.sme.frsl.ui;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.ui.BaseEditor;
import org.eclipse.sme.frsl.ui.internal.FRSLActivator;

public class FRSLEditor extends BaseEditor
{
	public static final String EDITOR_ID = FRSLActivator.ORG_ECLIPSE_SME_FRSL_FRSL;

	public FRSLEditor() {
		super();
	}

	@Override
	public @NonNull String getMarkerId() {
		return FRSLUiModule.MARKER_ID;
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId("#FRSLEditorContext"); //$NON-NLS-1$
		setRulerContextMenuId("#FRSLRulerContext"); //$NON-NLS-1$
		//		setHelpContextId(ITextEditorHelpContextIds.TEXT_EDITOR);
	}
	
	@Override
	public void doSaveAs() {
		
//		Status status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: FRSLEditor@doSaveAs **** ");
//		ILog log = Platform.getLog(FRSLEditor.class);
//		log.log(status);
		
		super.doSaveAs();
	}
}