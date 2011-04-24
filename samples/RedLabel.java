
import javax.swing.*;
import java.awt.*;
import java.util.TimeZone;

public class RedLabel extends JLabel {
  public RedLabel() {
    this.setForeground( Color.red );
    this.setFont( Font.decode( "VERDANA-BOLD-24" ) );
  }

  public void setTimeZone(TimeZone tz) {

    this.setText( tz.getDisplayName() );
  }
}
