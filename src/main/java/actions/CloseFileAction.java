package actions;

import ui.PJApp;

import java.awt.event.ActionEvent;

public class CloseFileAction extends PJAction {
  public CloseFileAction(PJApp app) {
    super(app);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    app.getTabs().removeSelectedEditor();
  }
}
