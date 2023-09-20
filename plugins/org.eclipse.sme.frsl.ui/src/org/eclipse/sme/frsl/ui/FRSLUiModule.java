/*******************************************************************************
 * Copyright (c) 2021 the VNU-SME lab.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Duc-Hanh Dang and the VNU-SME lab
 *******************************************************************************/
package org.eclipse.sme.frsl.ui;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.sme.frsl.ui.internal.FRSLActivator;
import org.eclipse.sme.frsl.ui.model.FRSLDocument;
import org.eclipse.sme.frsl.ui.model.FRSLDocumentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.IXtextEditorCallback;
import org.eclipse.xtext.ui.editor.model.IResourceForEditorInputFactory;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class FRSLUiModule extends org.eclipse.sme.frsl.ui.AbstractFRSLUiModule
{
	public static final @NonNull String PLUGIN_ID = "org.eclipse.sme.frsl.ui";
	public static final @NonNull String EDITOR_ID = FRSLActivator.ORG_ECLIPSE_SME_FRSL_FRSL;
	public static final @NonNull String MARKER_ID = "org.eclipse.sme.frsl.ui.Marker";

	private static EMFPlugin.InternalHelper helper = new EMFPlugin.InternalHelper(FRSLActivator.getInstance());

	/**
	 * Return the optionally translated value of a plugin.properties key.
	 */
	public static String getString(String key, boolean translate) {
		return helper.getString(key, translate);
	}

	/**
	 * Return the optionally translated value of a plugin.properties key with substitutions.
	 */
	public static String getString(String key, Object [] substitutions, boolean translate) {
		return helper.getString(key, substitutions, translate);
	}

	public FRSLUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

//	@Override
	@Override
	public Class<? extends XtextDocument> bindXtextDocument() {
		return FRSLDocument.class;
	}

	public Class<? extends XtextDocumentProvider> bindXtextDocumentProvider() {
		return FRSLDocumentProvider.class;
	}

	@Override
	public Class<? extends IResourceForEditorInputFactory> bindIResourceForEditorInputFactory() {
		return FRSLResourceForEditorInputFactory.class;
	}

	@Override
	public Class<? extends IXtextEditorCallback> bindIXtextEditorCallback() {
		return FRSLEditorCallback.class;
	}
}
