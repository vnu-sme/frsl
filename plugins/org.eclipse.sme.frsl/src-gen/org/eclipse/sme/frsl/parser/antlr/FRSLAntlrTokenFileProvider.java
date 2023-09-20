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

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class FRSLAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/sme/frsl/parser/antlr/internal/InternalFRSL.tokens");
	}
}
