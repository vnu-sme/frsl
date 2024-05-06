# Functional Requirements Specification Language (FRSL)
# current version: 1.0.0

**Prerequisites:**
- Install [Eclipse DSL Tools 2023-12](https://www.eclipse.org/downloads/packages/release/2023-09/r/eclipse-ide-java-and-dsl-developers).
- Install UML2 Extender SDK 5.5.3 (by Update Site: https://eclipse.dev/modeling/mdt/downloads/?project=uml2). 
- Clone this repo.
- Open repo: 
  - *File -> Open Project From File System -> Specify to folder 'frsl' -> Deselect the 'frsl' checkbox -> Select 'Search for nested projects' -> Finish*.
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

- To integrate current frsl model2model plugins, you need to ATL 4.8 by the update site: https://eclipse.dev/atl/downloads
- For frsl model2text plugins, you need to install Acceleo 3.7 by the update site: https://eclipse.dev/acceleo/download.html
- Jdk-20 is recommended for current frsl model2text (Acceleo) plugins.
