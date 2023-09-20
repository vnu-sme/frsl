/*******************************************************************************
 * Copyright (c) 2010, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.atl.frsl2uml.files;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.m2m.atl.common.ATLExecutionException;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;
import org.eclipse.sme.frsl.FrslPackage;

/**
 * Entry point of the 'Frsl2Sm' transformation module.
 */
public class Frsl2Sm {

	/**
	 * The property file. Stores module list, the metamodel and library locations.
	 * @generated
	 */
	private Properties properties;
	
	/**
	 * The IN model.
	 * @generated
	 */
	protected IModel inModel;	
	
	/**
	 * The IN2 model.
	 * @generated
	 */
	protected IModel in2Model;
	
	/**
	 * The TYPES model.
	 * @generated
	 */
	protected IModel typesModel;

	/**
	 * The OUT model.
	 * @generated
	 */
	protected IModel outModel;	
		
	/**
	 * The main method.
	 * 
	 * @param args
	 *            are the arguments
	 * @generated
	 */
	public static void main(String[] args) {
		try {
			if (args.length < 4) {
				System.out.println("Arguments not valid : {IN_model_path, IN2_model_path, TYPES_model_path, OUT_model_path}.");
			} else {
				Frsl2Sm runner = new Frsl2Sm();
				runner.loadModels(args[0], args[1], args[2]);
				runner.doFrsl2Sm(new NullProgressMonitor());
				runner.saveModels(args[3]);
			}
		} catch (ATLCoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ATLExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor.
	 *
	 * @generated NOT
	 */
	public Frsl2Sm() throws IOException {
		properties = new Properties();
		properties.load(getFileURL("Frsl2Sm.properties").openStream());
		EPackage.Registry.INSTANCE.put(getMetamodelUri("Uml"), org.eclipse.uml2.uml.UMLPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
	}
	
	/**
	 * Load the input and input/output models, initialize output models.
	 * 
	 * @param inModelPath
	 *            the IN model path
	 * @param in2ModelPath
	 *            the IN2 model path
	 * @param typesModelPath
	 *            the TYPES model path
	 * @throws ATLCoreException
	 *             if a problem occurs while loading models
	 *
	 * @generated
	 */
	public void loadModels(String inModelPath, String in2ModelPath, String typesModelPath) throws ATLCoreException {
		ModelFactory factory = new EMFModelFactory();
		IInjector injector = new EMFInjector();
	 	IReferenceModel frslMetamodel = factory.newReferenceModel();
		injector.inject(frslMetamodel, getMetamodelUri("Frsl"));
	 	IReferenceModel umlMetamodel = factory.newReferenceModel();
		injector.inject(umlMetamodel, getMetamodelUri("Uml"));
	 	IReferenceModel pivotMetamodel = factory.newReferenceModel();
		injector.inject(pivotMetamodel, getMetamodelUri("Pivot"));
		this.inModel = factory.newModel(frslMetamodel);
		injector.inject(inModel, inModelPath);
		this.in2Model = factory.newModel(pivotMetamodel);
		injector.inject(in2Model, in2ModelPath);
		this.typesModel = factory.newModel(umlMetamodel);
		injector.inject(typesModel, typesModelPath);
		this.outModel = factory.newModel(umlMetamodel);
	}
	
	public void loadModels(InputStream inModelPath, String in2ModelPath, String typesModelPath) throws ATLCoreException {
		ModelFactory factory = new EMFModelFactory();
		IInjector injector = new EMFInjector();
	 	IReferenceModel frslMetamodel = factory.newReferenceModel();
		injector.inject(frslMetamodel, getMetamodelUri("Frsl"));
	 	IReferenceModel umlMetamodel = factory.newReferenceModel();
		injector.inject(umlMetamodel, getMetamodelUri("Uml"));
		
		IReferenceModel pivotMetamodel = factory.newReferenceModel();
		injector.inject(pivotMetamodel, getMetamodelUri("Pivot"));
		
		this.inModel = factory.newModel(frslMetamodel);
		injector.inject(inModel, inModelPath, null);
		
		this.in2Model = factory.newModel(pivotMetamodel);
		injector.inject(in2Model, in2ModelPath);
		
		this.typesModel = factory.newModel(umlMetamodel);
		injector.inject(typesModel, typesModelPath);
		
		this.outModel = factory.newModel(umlMetamodel);
	}

	/**
	 * Save the output and input/output models.
	 * 
	 * @param outModelPath
	 *            the OUT model path
	 * @throws ATLCoreException
	 *             if a problem occurs while saving models
	 *
	 * @generated
	 */
	public void saveModels(String outModelPath) throws ATLCoreException {
		IExtractor extractor = new EMFExtractor();
		extractor.extract(outModel, outModelPath);
	}

	/**
	 * Transform the models.
	 * 
	 * @param monitor
	 *            the progress monitor
	 * @throws ATLCoreException
	 *             if an error occurs during models handling
	 * @throws IOException
	 *             if a module cannot be read
	 * @throws ATLExecutionException
	 *             if an error occurs during the execution
	 *
	 * @generated
	 */
	public Object doFrsl2Sm(IProgressMonitor monitor) throws ATLCoreException, IOException, ATLExecutionException {
		ILauncher launcher = new EMFVMLauncher();
		Map<String, Object> launcherOptions = getOptions();
		launcher.initialize(launcherOptions);
		launcher.addInModel(inModel, "IN", "Frsl");
		launcher.addInModel(in2Model, "IN2", "Pivot");
		launcher.addInModel(typesModel, "TYPES", "Uml");
		launcher.addOutModel(outModel, "OUT", "Uml");
		return launcher.launch("run", monitor, launcherOptions, (Object[]) getModulesList());
	}
	
	/**
	 * Returns an Array of the module input streams, parameterized by the
	 * property file.
	 * 
	 * @return an Array of the module input streams
	 * @throws IOException
	 *             if a module cannot be read
	 *
	 * @generated
	 */
	protected InputStream[] getModulesList() throws IOException {
		InputStream[] modules = null;
		String modulesList = properties.getProperty("Frsl2Sm.modules");
		if (modulesList != null) {
			String[] moduleNames = modulesList.split(",");
			modules = new InputStream[moduleNames.length];
			for (int i = 0; i < moduleNames.length; i++) {
				String asmModulePath = new Path(moduleNames[i].trim()).removeFileExtension().addFileExtension("asm").toString();
				modules[i] = getFileURL(asmModulePath).openStream();
			}
		}
		return modules;
	}
	
	/**
	 * Returns the URI of the given metamodel, parameterized from the property file.
	 * 
	 * @param metamodelName
	 *            the metamodel name
	 * @return the metamodel URI
	 *
	 * @generated
	 */
	protected String getMetamodelUri(String metamodelName) {
		return properties.getProperty("Frsl2Sm.metamodels." + metamodelName);
	}
	
	/**
	 * Returns the file name of the given library, parameterized from the property file.
	 * 
	 * @param libraryName
	 *            the library name
	 * @return the library file name
	 *
	 * @generated
	 */
	protected InputStream getLibraryAsStream(String libraryName) throws IOException {
		return getFileURL(properties.getProperty("Frsl2Sm.libraries." + libraryName)).openStream();
	}
	
	/**
	 * Returns the options map, parameterized from the property file.
	 * 
	 * @return the options map
	 *
	 * @generated
	 */
	protected Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		for (Entry<Object, Object> entry : properties.entrySet()) {
			if (entry.getKey().toString().startsWith("Frsl2Sm.options.")) {
				options.put(entry.getKey().toString().replaceFirst("Frsl2Sm.options.", ""), 
				entry.getValue().toString());
			}
		}
		return options;
	}
	
	/**
	 * Finds the file in the plug-in. Returns the file URL.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the file URL
	 * @throws IOException
	 *             if the file doesn't exist
	 * 
	 * @generated
	 */
	protected static URL getFileURL(String fileName) throws IOException {
		final URL fileURL;
		if (isEclipseRunning()) {
			URL resourceURL = Frsl2Sm.class.getResource(fileName);
			if (resourceURL != null) {
				fileURL = FileLocator.toFileURL(resourceURL);
			} else {
				fileURL = null;
			}
		} else {
			fileURL = Frsl2Sm.class.getResource(fileName);
		}
		if (fileURL == null) {
			throw new IOException("'" + fileName + "' not found");
		} else {
			return fileURL;
		}
	}

	/**
	 * Tests if eclipse is running.
	 * 
	 * @return <code>true</code> if eclipse is running
	 *
	 * @generated
	 */
	public static boolean isEclipseRunning() {
		try {
			return Platform.isRunning();
		} catch (Throwable exception) {
			// Assume that we aren't running.
		}
		return false;
	}
}
