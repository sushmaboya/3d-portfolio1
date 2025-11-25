 #!/bin/bash
javac -cp .:path/to/javafx-sdk/lib/* Simple3DGraphics.java Vector3D.java GraphicsConfig.java
java --module-path path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml Simple3DGraphics
