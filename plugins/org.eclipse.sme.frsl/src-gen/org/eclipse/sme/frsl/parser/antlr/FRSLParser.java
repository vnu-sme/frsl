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
package org.eclipse.sme.frsl.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.sme.frsl.services.FRSLGrammarAccess;

public class FRSLParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {

	@Inject
	private FRSLGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}

	@Override
	protected org.eclipse.sme.frsl.parser.antlr.internal.InternalFRSLParser createParser(XtextTokenStream stream) {
		return new org.eclipse.sme.frsl.parser.antlr.internal.InternalFRSLParser(stream, getGrammarAccess());
	}

	@Override
	protected String getDefaultRuleName() {
		return "FrslModelCS";
	}

	public FRSLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(FRSLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

}
