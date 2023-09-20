package org.eclipse.sme.frsl.utilities;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.base.utilities.PivotResourceValidator;
import org.eclipse.sme.frslcs.FrslModelCS;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.AbstractInjectableValidator;
import org.eclipse.xtext.validation.CancelableDiagnostician;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.validation.impl.ConcreteSyntaxEValidator;

import com.google.common.collect.Lists;

public class FrslResourceValidator extends PivotResourceValidator {

	// FIXME BUG 389675 Remove duplication with respect to inherited method
	@Override
	public List<Issue> validate(Resource resource, final CheckMode mode, CancelIndicator mon) {
		//		System.out.println(Thread.currentThread().getName() + " validate start " + NameUtil.debugSimpleName(resource));
		//		System.out.println(new Date() + " Validate " + mode + " : " + csResource.getURI() + " on " + Thread.currentThread().getName());
		
		//Status status;
		//ILog log = Platform.getLog(getClass());		
//		status = new Status(IStatus.INFO, "org.eclipse.sme.frsl", "hanhdd: PivotResourceValidator$validate *** I'm here!!!"
//				+ "\n***resource = "  + resource
//				+ "\n***resourceSet = "  + resource.getResourceSet()
//				);
//		log.log(status);
		
	//	ThreadLocalExecutor.reset();
		assert ThreadLocalExecutor.basicGetEnvironmentFactory() != null;
		final CancelIndicator monitor = mon == null ? CancelIndicator.NullImpl : mon;
		resolveProxies(resource, monitor);
		if (monitor.isCanceled())
			return Collections.emptyList();

		final List<Issue> result = Lists.newArrayListWithExpectedSize(resource.getErrors().size()
			+ resource.getWarnings().size());
		try {
			IAcceptor<Issue> acceptor = createAcceptor(result);
			// Syntactical and linking errors
			// Collect EMF Resource Diagnostics
			if (mode.shouldCheck(CheckType.FAST)) {
				for (int i = 0; i < resource.getErrors().size(); i++) {
					if (monitor.isCanceled())
						return Collections.emptyList();
					issueFromXtextResourceDiagnostic(resource.getErrors().get(i), Severity.ERROR, acceptor);
				}

				for (int i = 0; i < resource.getWarnings().size(); i++) {
					if (monitor.isCanceled())
						return Collections.emptyList();
					issueFromXtextResourceDiagnostic(resource.getWarnings().get(i), Severity.WARNING, acceptor);
				}
			}

			if (monitor.isCanceled())
				return Collections.emptyList();
			
			// Validation errors
			// Collect validator Diagnostics
			for (EObject ele : resource.getContents()) {
				if(ele instanceof FrslModelCS) {
					ele = ( (FrslModelCS) ele).getDomainModel();
				}
				
				
				try {
					if (monitor.isCanceled())
						return Collections.emptyList();
					Diagnostician diagnostician = getDiagnostician();
					Map<Object, Object> options = LabelUtil.createDefaultContext(diagnostician);
					options.put(CheckMode.KEY, mode);
					options.put(CancelableDiagnostician.CANCEL_INDICATOR, monitor);
					// disable concrete syntax validation, since a semantic model that has been parsed
					// from the concrete syntax always complies with it - otherwise there are parse errors.
					options.put(ConcreteSyntaxEValidator.DISABLE_CONCRETE_SYNTAX_EVALIDATOR, Boolean.TRUE);
					// see EObjectValidator.getRootEValidator(Map<Object, Object>)
					boolean hasSyntaxError = false;
					if (resource instanceof XtextResource) {
						options.put(AbstractInjectableValidator.CURRENT_LANGUAGE_NAME, ((XtextResource) resource).getLanguageName());
						if (resource instanceof BaseCSResource) {
							BaseCSResource csResource = (BaseCSResource)resource;
							@NonNull List<Resource.Diagnostic> errors = csResource.getErrors();
							hasSyntaxError = ElementUtil.hasSyntaxError(errors);
							if (hasSyntaxError) {
								options.put(PivotResourceValidator.HAS_SYNTAX_ERRORS, Boolean.TRUE);
							}
						}
					}
					if (!hasSyntaxError) {
						Diagnostic diagnostic = getDiagnostician().validate(ele, options);
						if (!diagnostic.getChildren().isEmpty()) {
							for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
								issueFromEValidatorDiagnostic(childDiagnostic, acceptor);
							}
						} else {
							issueFromEValidatorDiagnostic(diagnostic, acceptor);
						}
					}
				} catch (RuntimeException e) {
					if (!monitor.isCanceled()) {		// Fix Bug 462544 working around Xtext Bug 461764
						//log.error(e.getMessage(), e);
					}
				}
			}
		} catch (RuntimeException e) {
			//log.error(e.getMessage(), e);
		}
		if (monitor.isCanceled())
			return Collections.emptyList();
		if (resource instanceof BaseCSResource) {
			BaseCSResource csResource = (BaseCSResource)resource;
			CS2AS cs2as = csResource.findCS2AS();
			if (cs2as != null) {
				Resource asResource = cs2as.getASResource();
				IAcceptor<Issue> acceptor = createAcceptor(result);
				//				if (mode.shouldCheck(CheckType.EXPENSIVE)) {
				performValidation(acceptor, asResource, monitor);
				//				}
				//				else {
				//					reuseValidation(acceptor, asResource, monitor);
				//				}
			}
		}
		//		System.out.println(Thread.currentThread().getName() + " validate end " + NameUtil.debugSimpleName(resource));
		ThreadLocalExecutor.resetEnvironmentFactory();
		return result;
	}

}
