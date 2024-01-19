# Functional Requirements Specification Language (FRSL)
# frsl-1.0.0

**Prerequisites:**
- Install [Eclipse DSL Tools (Version: 2023-09)](https://www.eclipse.org/downloads/packages/release/2023-09/r/eclipse-ide-java-and-dsl-developers).
- Install UML2 Extender SDK (by Local Update Site: platform:/resource/additional-plugins/mdt-uml2-Update-I202103231848.zip). 
- Clone this repo.
- Open repo: 
  - *File -> Open Project From File System -> Specify to folder 'frsl-1.0.0' -> Deselect the 'frsl-1.0.0' checkbox -> Select 'Search for nested projects' -> Finish*.
- In *Problem* section (in Eclipse):
  - If any project is missing *src-gen*, add manually folder *src-gen* to that project.
  - Fix the error 'An API baseline has not been set for the current workspace": (1) Click the menu: Windows\Preferences\Plug-in Development\API Baselines; (2) Update the option "Missing API baseline": Error -> Warning
  - ...
   
**Usage\:**

- Run runtime: *Right click to any project -> Run As -> Eclipse Application*.
- In runtime environment, create general project: *File -> New Project -> General -> Project -> Next ...*
- In general project, create .frsl file: *Right click -> New -> File -> Set name "test.frsl" -> Finish*
- Generate the *.frslas* file from the *.frsl* file:
  - Open the *.frsl* file -> Right click on white space in file -> FRSL -> Save As -> FRSLAS.

**Notes:**

- Local Update Sites for the optional plugins for model2model (ATL) and model2text (Acceleo) are also located here: platform:/resource/additional-plugins/
- Java 16 or 19 are preferred. Newer Java versions (e.g. 20) are not recommended.