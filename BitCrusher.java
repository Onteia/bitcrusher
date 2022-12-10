package com.onteia.bitcrusher;


import voltage.controllers.*;
import voltage.core.*;
import voltage.core.Jack.JackType;
import voltage.sources.*;
import voltage.utility.*;
import voltage.processors.*;
import voltage.effects.*;
import java.awt.*;

//[user-imports]   Add your own imports here
//[/user-imports]


public class BitCrusher extends VoltageModule
//[user-inheritance]
//[/user-inheritance]
{

public BitCrusher( long moduleID, VoltageObjects voltageObjects )
{
   super( moduleID, voltageObjects, "BitCrusher", ModuleType.ModuleType_Processor, 2.0 );

   InitializeControls();


   canBeBypassed = false;
   SetSkin( "137c25b9d2b74dd9aa32cdecef356d6e" );
}

void InitializeControls()
{

   knobSamples = new VoltageKnob( "knobSamples", "Samples Knob", this, 0.0, 1.0, 1 );
   AddComponent( knobSamples );
   knobSamples.SetWantsMouseNotifications( false );
   knobSamples.SetPosition( 12, 127 );
   knobSamples.SetSize( 35, 35 );
   knobSamples.SetSkin( "Plastic White" );
   knobSamples.SetRange( 0.0, 1.0, 1, false, 0 );
   knobSamples.SetKnobParams( 215, 145 );
   knobSamples.DisplayValueInPercent( false );
   knobSamples.SetKnobAdjustsRing( true );

   knobResolution = new VoltageKnob( "knobResolution", "Resolution Knob", this, 0.0, 1.0, 1 );
   AddComponent( knobResolution );
   knobResolution.SetWantsMouseNotifications( false );
   knobResolution.SetPosition( 13, 184 );
   knobResolution.SetSize( 34, 34 );
   knobResolution.SetSkin( "Plastic White" );
   knobResolution.SetRange( 0.0, 1.0, 1, false, 0 );
   knobResolution.SetKnobParams( 215, 145 );
   knobResolution.DisplayValueInPercent( false );
   knobResolution.SetKnobAdjustsRing( true );

   knobDistortion = new VoltageKnob( "knobDistortion", "Distortion Knob", this, 0.0, 1.0, 0.0 );
   AddComponent( knobDistortion );
   knobDistortion.SetWantsMouseNotifications( false );
   knobDistortion.SetPosition( 12, 62 );
   knobDistortion.SetSize( 36, 36 );
   knobDistortion.SetSkin( "Plastic White" );
   knobDistortion.SetRange( 0.0, 1.0, 0.0, false, 0 );
   knobDistortion.SetKnobParams( 215, 145 );
   knobDistortion.DisplayValueInPercent( false );
   knobDistortion.SetKnobAdjustsRing( true );

   inputL = new VoltageAudioJack( "inputL", "Left Input", this, JackType.JackType_AudioInput );
   AddComponent( inputL );
   inputL.SetWantsMouseNotifications( false );
   inputL.SetPosition( 5, 251 );
   inputL.SetSize( 37, 37 );
   inputL.SetSkin( "Jack Straight" );

   inputR = new VoltageAudioJack( "inputR", "Right Input", this, JackType.JackType_AudioInput );
   AddComponent( inputR );
   inputR.SetWantsMouseNotifications( false );
   inputR.SetPosition( 5, 300 );
   inputR.SetSize( 37, 37 );
   inputR.SetSkin( "Jack Straight" );

   outputL = new VoltageAudioJack( "outputL", "Left Output", this, JackType.JackType_AudioOutput );
   AddComponent( outputL );
   outputL.SetWantsMouseNotifications( false );
   outputL.SetPosition( 101, 251 );
   outputL.SetSize( 37, 37 );
   outputL.SetSkin( "Jack Straight" );

   outputR = new VoltageAudioJack( "outputR", "Right Output", this, JackType.JackType_AudioOutput );
   AddComponent( outputR );
   outputR.SetWantsMouseNotifications( false );
   outputR.SetPosition( 101, 300 );
   outputR.SetSize( 37, 37 );
   outputR.SetSkin( "Jack Straight" );

   labelInput = new VoltageLabel( "labelInput", "Input Label", this, "input" );
   AddComponent( labelInput );
   labelInput.SetWantsMouseNotifications( false );
   labelInput.SetPosition( 1, 229 );
   labelInput.SetSize( 47, 30 );
   labelInput.SetEditable( false, false );
   labelInput.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelInput.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelInput.SetColor( new Color( 255, 255, 255, 255 ) );
   labelInput.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelInput.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelInput.SetBorderSize( 1 );
   labelInput.SetMultiLineEdit( false );
   labelInput.SetIsNumberEditor( false );
   labelInput.SetNumberEditorRange( 0, 100 );
   labelInput.SetNumberEditorInterval( 1 );
   labelInput.SetNumberEditorUsesMouseWheel( false );
   labelInput.SetHasCustomTextHoverColor( false );
   labelInput.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelInput.SetFont( "<Sans-Serif>", 14, false, false );

   labelIL = new VoltageLabel( "labelIL", "Input L Label", this, "L" );
   AddComponent( labelIL );
   labelIL.SetWantsMouseNotifications( false );
   labelIL.SetPosition( 14, 280 );
   labelIL.SetSize( 19, 30 );
   labelIL.SetEditable( false, false );
   labelIL.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelIL.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelIL.SetColor( new Color( 255, 255, 255, 255 ) );
   labelIL.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelIL.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelIL.SetBorderSize( 1 );
   labelIL.SetMultiLineEdit( false );
   labelIL.SetIsNumberEditor( false );
   labelIL.SetNumberEditorRange( 0, 100 );
   labelIL.SetNumberEditorInterval( 1 );
   labelIL.SetNumberEditorUsesMouseWheel( false );
   labelIL.SetHasCustomTextHoverColor( false );
   labelIL.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelIL.SetFont( "<Sans-Serif>", 14, false, false );

   labelIR = new VoltageLabel( "labelIR", "Input R Label", this, "R" );
   AddComponent( labelIR );
   labelIR.SetWantsMouseNotifications( false );
   labelIR.SetPosition( 15, 330 );
   labelIR.SetSize( 18, 30 );
   labelIR.SetEditable( false, false );
   labelIR.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelIR.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelIR.SetColor( new Color( 255, 255, 255, 255 ) );
   labelIR.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelIR.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelIR.SetBorderSize( 1 );
   labelIR.SetMultiLineEdit( false );
   labelIR.SetIsNumberEditor( false );
   labelIR.SetNumberEditorRange( 0, 100 );
   labelIR.SetNumberEditorInterval( 1 );
   labelIR.SetNumberEditorUsesMouseWheel( false );
   labelIR.SetHasCustomTextHoverColor( false );
   labelIR.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelIR.SetFont( "<Sans-Serif>", 14, false, false );

   labelOL = new VoltageLabel( "labelOL", "Output L Label", this, "L" );
   AddComponent( labelOL );
   labelOL.SetWantsMouseNotifications( false );
   labelOL.SetPosition( 111, 279 );
   labelOL.SetSize( 17, 30 );
   labelOL.SetEditable( false, false );
   labelOL.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelOL.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelOL.SetColor( new Color( 255, 255, 255, 255 ) );
   labelOL.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelOL.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelOL.SetBorderSize( 1 );
   labelOL.SetMultiLineEdit( false );
   labelOL.SetIsNumberEditor( false );
   labelOL.SetNumberEditorRange( 0, 100 );
   labelOL.SetNumberEditorInterval( 1 );
   labelOL.SetNumberEditorUsesMouseWheel( false );
   labelOL.SetHasCustomTextHoverColor( false );
   labelOL.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelOL.SetFont( "<Sans-Serif>", 14, false, false );

   textLabel5 = new VoltageLabel( "textLabel5", "textLabel5", this, "output" );
   AddComponent( textLabel5 );
   textLabel5.SetWantsMouseNotifications( false );
   textLabel5.SetPosition( 94, 229 );
   textLabel5.SetSize( 50, 30 );
   textLabel5.SetEditable( false, false );
   textLabel5.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   textLabel5.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   textLabel5.SetColor( new Color( 255, 255, 255, 255 ) );
   textLabel5.SetBkColor( new Color( 65, 65, 65, 0 ) );
   textLabel5.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   textLabel5.SetBorderSize( 1 );
   textLabel5.SetMultiLineEdit( false );
   textLabel5.SetIsNumberEditor( false );
   textLabel5.SetNumberEditorRange( 0, 100 );
   textLabel5.SetNumberEditorInterval( 1 );
   textLabel5.SetNumberEditorUsesMouseWheel( false );
   textLabel5.SetHasCustomTextHoverColor( false );
   textLabel5.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   textLabel5.SetFont( "<Sans-Serif>", 14, false, false );

   labelOR = new VoltageLabel( "labelOR", "Output R Label", this, "R" );
   AddComponent( labelOR );
   labelOR.SetWantsMouseNotifications( false );
   labelOR.SetPosition( 111, 330 );
   labelOR.SetSize( 17, 30 );
   labelOR.SetEditable( false, false );
   labelOR.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelOR.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelOR.SetColor( new Color( 255, 255, 255, 255 ) );
   labelOR.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelOR.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelOR.SetBorderSize( 1 );
   labelOR.SetMultiLineEdit( false );
   labelOR.SetIsNumberEditor( false );
   labelOR.SetNumberEditorRange( 0, 100 );
   labelOR.SetNumberEditorInterval( 1 );
   labelOR.SetNumberEditorUsesMouseWheel( false );
   labelOR.SetHasCustomTextHoverColor( false );
   labelOR.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelOR.SetFont( "<Sans-Serif>", 14, false, false );

   textLabel7 = new VoltageLabel( "textLabel7", "textLabel7", this, "bitcrusher" );
   AddComponent( textLabel7 );
   textLabel7.SetWantsMouseNotifications( false );
   textLabel7.SetPosition( 37, 0 );
   textLabel7.SetSize( 70, 30 );
   textLabel7.SetEditable( false, false );
   textLabel7.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   textLabel7.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   textLabel7.SetColor( new Color( 255, 255, 255, 255 ) );
   textLabel7.SetBkColor( new Color( 65, 65, 65, 0 ) );
   textLabel7.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   textLabel7.SetBorderSize( 1 );
   textLabel7.SetMultiLineEdit( false );
   textLabel7.SetIsNumberEditor( false );
   textLabel7.SetNumberEditorRange( 0, 100 );
   textLabel7.SetNumberEditorInterval( 1 );
   textLabel7.SetNumberEditorUsesMouseWheel( false );
   textLabel7.SetHasCustomTextHoverColor( false );
   textLabel7.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   textLabel7.SetFont( "<Sans-Serif>", 14, false, false );

   labelDistortion = new VoltageLabel( "labelDistortion", "Distortion Label", this, "Distort Amount" );
   AddComponent( labelDistortion );
   labelDistortion.SetWantsMouseNotifications( false );
   labelDistortion.SetPosition( 3, 30 );
   labelDistortion.SetSize( 53, 30 );
   labelDistortion.SetEditable( false, false );
   labelDistortion.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelDistortion.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelDistortion.SetColor( new Color( 255, 255, 255, 255 ) );
   labelDistortion.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelDistortion.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelDistortion.SetBorderSize( 1 );
   labelDistortion.SetMultiLineEdit( false );
   labelDistortion.SetIsNumberEditor( false );
   labelDistortion.SetNumberEditorRange( 0, 100 );
   labelDistortion.SetNumberEditorInterval( 1 );
   labelDistortion.SetNumberEditorUsesMouseWheel( false );
   labelDistortion.SetHasCustomTextHoverColor( false );
   labelDistortion.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelDistortion.SetFont( "<Sans-Serif>", 14, false, false );

   textLabel9 = new VoltageLabel( "textLabel9", "textLabel9", this, "Samples" );
   AddComponent( textLabel9 );
   textLabel9.SetWantsMouseNotifications( false );
   textLabel9.SetPosition( 0, 98 );
   textLabel9.SetSize( 59, 30 );
   textLabel9.SetEditable( false, false );
   textLabel9.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   textLabel9.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   textLabel9.SetColor( new Color( 255, 255, 255, 255 ) );
   textLabel9.SetBkColor( new Color( 65, 65, 65, 0 ) );
   textLabel9.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   textLabel9.SetBorderSize( 1 );
   textLabel9.SetMultiLineEdit( false );
   textLabel9.SetIsNumberEditor( false );
   textLabel9.SetNumberEditorRange( 0, 100 );
   textLabel9.SetNumberEditorInterval( 1 );
   textLabel9.SetNumberEditorUsesMouseWheel( false );
   textLabel9.SetHasCustomTextHoverColor( false );
   textLabel9.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   textLabel9.SetFont( "<Sans-Serif>", 14, false, false );

   textLabel10 = new VoltageLabel( "textLabel10", "textLabel10", this, "Resolution" );
   AddComponent( textLabel10 );
   textLabel10.SetWantsMouseNotifications( false );
   textLabel10.SetPosition( 0, 155 );
   textLabel10.SetSize( 65, 30 );
   textLabel10.SetEditable( false, false );
   textLabel10.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   textLabel10.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   textLabel10.SetColor( new Color( 255, 255, 255, 255 ) );
   textLabel10.SetBkColor( new Color( 65, 65, 65, 0 ) );
   textLabel10.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   textLabel10.SetBorderSize( 1 );
   textLabel10.SetMultiLineEdit( false );
   textLabel10.SetIsNumberEditor( false );
   textLabel10.SetNumberEditorRange( 0, 100 );
   textLabel10.SetNumberEditorInterval( 1 );
   textLabel10.SetNumberEditorUsesMouseWheel( false );
   textLabel10.SetHasCustomTextHoverColor( false );
   textLabel10.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   textLabel10.SetFont( "<Sans-Serif>", 14, false, false );

   toggleDistort = new VoltageToggle( "toggleDistort", "Distortion Toggle", this, false, 0 );
   AddComponent( toggleDistort );
   toggleDistort.SetWantsMouseNotifications( false );
   toggleDistort.SetPosition( 91, 90 );
   toggleDistort.SetSize( 31, 31 );
   toggleDistort.SetSkin( "Blue Square" );
   toggleDistort.ShowOverlay( false );
   toggleDistort.SetOverlayText( "" );

   labelToggle = new VoltageLabel( "labelToggle", "Distort Toggle Label", this, "Distort After Samples" );
   AddComponent( labelToggle );
   labelToggle.SetWantsMouseNotifications( false );
   labelToggle.SetPosition( 79, 60 );
   labelToggle.SetSize( 58, 30 );
   labelToggle.SetEditable( false, false );
   labelToggle.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelToggle.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelToggle.SetColor( new Color( 255, 255, 255, 255 ) );
   labelToggle.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelToggle.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelToggle.SetBorderSize( 1 );
   labelToggle.SetMultiLineEdit( false );
   labelToggle.SetIsNumberEditor( false );
   labelToggle.SetNumberEditorRange( 0, 100 );
   labelToggle.SetNumberEditorInterval( 1 );
   labelToggle.SetNumberEditorUsesMouseWheel( false );
   labelToggle.SetHasCustomTextHoverColor( false );
   labelToggle.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelToggle.SetFont( "<Sans-Serif>", 14, false, false );

   labelDebug = new VoltageLabel( "labelDebug", "Debug", this, "Text" );
   AddComponent( labelDebug );
   labelDebug.SetWantsMouseNotifications( false );
   labelDebug.SetPosition( 25, 211 );
   labelDebug.SetSize( 80, 30 );
   labelDebug.SetEditable( false, false );
   labelDebug.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   labelDebug.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   labelDebug.SetColor( new Color( 255, 255, 255, 255 ) );
   labelDebug.SetBkColor( new Color( 65, 65, 65, 0 ) );
   labelDebug.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   labelDebug.SetBorderSize( 1 );
   labelDebug.SetMultiLineEdit( false );
   labelDebug.SetIsNumberEditor( false );
   labelDebug.SetNumberEditorRange( 0, 100 );
   labelDebug.SetNumberEditorInterval( 1 );
   labelDebug.SetNumberEditorUsesMouseWheel( false );
   labelDebug.SetHasCustomTextHoverColor( false );
   labelDebug.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   labelDebug.SetFont( "<Sans-Serif>", 14, false, false );

   toggleSamples = new VoltageToggle( "toggleSamples", "Samples Toggle", this, false, 0 );
   AddComponent( toggleSamples );
   toggleSamples.SetWantsMouseNotifications( false );
   toggleSamples.SetPosition( 91, 158 );
   toggleSamples.SetSize( 31, 31 );
   toggleSamples.SetSkin( "Blue Square" );
   toggleSamples.ShowOverlay( false );
   toggleSamples.SetOverlayText( "" );

   textLabel13 = new VoltageLabel( "textLabel13", "textLabel13", this, "Samples After Resolution" );
   AddComponent( textLabel13 );
   textLabel13.SetWantsMouseNotifications( false );
   textLabel13.SetPosition( 79, 127 );
   textLabel13.SetSize( 61, 30 );
   textLabel13.SetEditable( false, false );
   textLabel13.SetJustificationFlags( VoltageLabel.Justification.HorizCentered );
   textLabel13.SetJustificationFlags( VoltageLabel.Justification.VertCentered );
   textLabel13.SetColor( new Color( 255, 255, 255, 255 ) );
   textLabel13.SetBkColor( new Color( 65, 65, 65, 0 ) );
   textLabel13.SetBorderColor( new Color( 0, 0, 0, 0 ) );
   textLabel13.SetBorderSize( 1 );
   textLabel13.SetMultiLineEdit( false );
   textLabel13.SetIsNumberEditor( false );
   textLabel13.SetNumberEditorRange( 0, 100 );
   textLabel13.SetNumberEditorInterval( 1 );
   textLabel13.SetNumberEditorUsesMouseWheel( false );
   textLabel13.SetHasCustomTextHoverColor( false );
   textLabel13.SetTextHoverColor( new Color( 0, 0, 0, 255 ) );
   textLabel13.SetFont( "<Sans-Serif>", 12, false, false );
}



//-------------------------------------------------------------------------------
//  public void Initialize()

//  Initialize will get called shortly after your module's constructor runs. You can use it to
//  do any initialization that the auto-generated code doesn't handle.
//-------------------------------------------------------------------------------
@Override
public void Initialize()
{
   //[user-Initialize]   Add your own initialization code here



   //[/user-Initialize]
}


//-------------------------------------------------------------------------------
//  public void Destroy()

//  Destroy will get called just before your module gets deleted. You can use it to perform any
//  cleanup that's not handled automatically by Java.
//-------------------------------------------------------------------------------
@Override
public void Destroy()
{
   super.Destroy();
   //[user-Destroy]   Add your own module-getting-deleted code here



   //[/user-Destroy]
}


//-------------------------------------------------------------------------------
//  public boolean Notify( VoltageComponent component, ModuleNotifications notification, double doubleValue, long longValue, int x, int y, Object object )

//  Notify will get called when various events occur - control values changing, timers firing, etc.
//-------------------------------------------------------------------------------
@Override
public boolean Notify( VoltageComponent component, ModuleNotifications notification, double doubleValue, long longValue, int x, int y, Object object )
{
   //[user-Notify]   Add your own notification handling code between this line and the notify-close comment
   switch( notification )
   {
      case Knob_Changed:   // doubleValue is the new VoltageKnob value
      {
      
         if(component == knobDistortion) {
            distortionAmount = doubleValue;
         } else if(component == knobSamples) {
            samplesAmount = doubleValue;
         } else if(component == knobResolution) {
            resolutionAmount = doubleValue;
         }
      
      }
      break;
   
      case Slider_Changed:   // doubleValue is the new slider value
      {
      }
      break;
   
      case Button_Changed:   // doubleValue is the new button/toggle button value
      {
         if(component == toggleDistort) {
            // distortAfter is true if doubleValue >= 0.5; false otherwise
            distortAfter = (doubleValue >= 0.5);
         } else if(component == toggleSamples) {
            samplesAfter = (doubleValue >= 0.5);
         }
      }
      break;
   
      case Switch_Changed:   // doubleValue is the new switch value
      {
      }
      break;
   
      case Jack_Connected:   // longValue is the new cable ID
      {
      }
      break;
   
      case Jack_Disconnected:   // All cables have been disconnected from this jack
      {
      }
      break;
   
      case GUI_Update_Timer:   // Called every 50ms (by default) if turned on
      {
      }
      break;
   
      case Object_MouseMove:   // called when mouse is over an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
      {
      }
      break;
   
      case Object_MouseLeave:  // called when mouse leaves an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
      {
      }
      break;
   
      case Object_LeftButtonDown:   // called when user left-clicks on an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
      {
      }
      break;
   
      case Object_LeftButtonUp:   // called when user releases left mouse button on an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
      {
      }
      break;
   
      case Object_RightButtonDown:   // called when user releases right mouse button on an object that receives mouse notifications. 'object' parameter is a VoltageMouseKeyFlags object.
      {
      }
      break;
   
      case Object_RightButtonUp:   // called when user right-clicks on an object that receives mouse notifications
      {
      }
      break;
   
      case Object_LeftButtonDoubleClick: // called when user left-button double-clicks on an object that receives mouse notifications
      {
      }
      break;
   
      // Less common notifications:
   
      case Named_Timer:   // object contains a String with the name of the timer that has fired
      {
      }
      break;
   
      case Canvas_Painting:   // About to paint canvas.  object is a java.awt.Rectangle with painting boundaries
      {
      }
      break;
   
      case Canvas_Painted:   // Canvas painting is complete
      {
      }
      break;
   
      case Control_DragStart:    // A user has started dragging on a control that has been marked as draggable
      {
      }
      break;
   
      case Control_DragOn:       // This control has been dragged over during a drag operation. object contains the dragged object
      {
      }
      break;
   
      case Control_DragOff:      // This control has been dragged over during a drag operation. object contains the dragged object
      {
      }
      break;
   
      case Control_DragEnd:      // A user has ended their drag on a control that has been marked as draggable
      {
      }
      break;
   
      case Label_Changed:        // The text of an editable text control has changed
      {
      }
      break;
   
      case SoundPlayback_Start:   // A sound has begun playback
      {
      }
      break;
   
      case SoundPlayback_End:     // A sound has ended playback
      {
      }
      break;
   
      case Scrollbar_Position:    // longValue is the new scrollbar position
      {
      }
      break;
   
      case PolyVoices_Changed:    // longValue is the new number of poly voices
      {
      }
      break;
   
      case File_Dropped:     // 'object' is a String containing the file path
      {
      }
      break;
   
      case Preset_Loading_Start:   // called when preset loading begins
      {
      }
      break;
   
      case Preset_Loading_Finish:  // called when preset loading finishes
      {
      }
      break;
   
      case Variation_Loading_Start:    // sent when a variation is about to load
      {
      }
      break;
   
      case Variation_Loading_Finish:   // sent when a variation has just finished loading
      {
      }
      break;
   
      case Tempo_Changed:     // doubleValue is the new tempo
      {
      }
      break;
   
      case Randomized:     // called when the module's controls get randomized
      {
      }
      break;
   
      case VariationListChanged:   // sent when a variation gets added, deleted, or renamed, or the variations list gets reordered
      {
      }
      break;
   
      case Key_Press:     // sent when module has keyboard focus and a key is pressed; object is a VoltageKeyPressInfo object
      {
      }
      break;
   
      case Reset:    // sent when the module has been reset to default settings
      {
      }
      break;
   
      case Keyboard_NoteOn:   // sent when a note has been pressed on a VoltageKeyboard object. longValue is the note value ( 0-127 )
      {
      }
      break;
   
      case Keyboard_NoteOff:   // sent when a note has been released on a VoltageKeyboard object. longValue is the note value ( 0-127 )
      {
      }
      break;
   
      case Curve_Changed:   // sent when user has edited a curve's value. 'object' will be a VoltageCurve.CurveChangeNotification object.
      {
      }
      break;
   }



   return false;
   //[/user-Notify]
}


//-------------------------------------------------------------------------------
//  public void ProcessSample()

//  ProcessSample is called once per sample. Usually it's where you read
//  from input jacks, process audio, and write it to your output jacks.
//  Since ProcesssSample gets called 48,000 times per second, offload CPU-intensive operations
//  to other threads when possible and avoid calling native functions.
//-------------------------------------------------------------------------------
@Override
public void ProcessSample()
{
   //[user-ProcessSample]   Add your own process-sampling code here



   //[/user-ProcessSample]
}


//-------------------------------------------------------------------------------
//  public String GetTooltipText( VoltageComponent component )

//  Gets called when a tooltip is about to display for a control. Override it if
//  you want to change what the tooltip displays - if you want a knob to work in logarithmic fashion,
//  for instance, you can translate the knob's current value to a log-based string and display it here.
//-------------------------------------------------------------------------------
@Override
public String GetTooltipText( VoltageComponent component )
{
   //[user-GetTooltipText]   Add your own code here



   return super.GetTooltipText( component );
   //[/user-GetTooltipText]
}


//-------------------------------------------------------------------------------
//  public void EditComponentValue( VoltageComponent component, double newValue, String newText )

//  Gets called after a user clicks on a tooltip and types in a new value for a control. Override this if
//  you've changed the default tooltip display (translating a linear value to logarithmic, for instance)
//  in GetTooltipText().
//-------------------------------------------------------------------------------
@Override
public void EditComponentValue( VoltageComponent component, double newValue, String newText )
{
   //[user-EditComponentValue]   Add your own code here



   //[/user-EditComponentValue]
   super.EditComponentValue( component, newValue, newText );
}


//-------------------------------------------------------------------------------
//  public void OnUndoRedo( String undoType, double newValue, Object optionalObject )

//  If you've created custom undo events via calls to CreateUndoEvent, you'll need to
//  process them in this function when they get triggered by undo/redo actions.
//-------------------------------------------------------------------------------
@Override
public void OnUndoRedo( String undoType, double newValue, Object optionalObject )
{
   //[user-OnUndoRedo]   Add your own code here



   //[/user-OnUndoRedo]
}


//-------------------------------------------------------------------------------
//  public byte[] GetStateInformation()

//  Gets called when the module's state gets saved, typically when the user saves a preset with
//  this module in it. Voltage Modular will automatically save the states of knobs, sliders, etc.,
//  but if you have any custom state information you need to save, return it from this function.
//-------------------------------------------------------------------------------
@Override
public byte[] GetStateInformation()
{
   //[user-GetStateInformation]   Add your own code here



   return null;
   //[/user-GetStateInformation]
}


//-------------------------------------------------------------------------------
//  public void SetStateInformation(byte[] stateInfo)

//  Gets called when this module's state is getting restored, typically when a user opens a preset with
//  this module in it. The stateInfo parameter will contain whatever custom data you stored in GetStateInformation().
//-------------------------------------------------------------------------------
@Override
public void SetStateInformation(byte[] stateInfo)
{
   //[user-SetStateInformation]   Add your own code here



   //[/user-SetStateInformation]
}


//-------------------------------------------------------------------------------
//  public byte[] GetStateInformationForVariations()

//  Gets called when a user saves a variation with this module in it.
//  Voltage Modular will automatically save the states of knobs, sliders, etc.,
//  but if you have any custom state information you need to save, return it from this function.
//-------------------------------------------------------------------------------
@Override
public byte[] GetStateInformationForVariations()
{
   //[user-GetStateInformationForVariations]   Add your own code here



   return GetStateInformation();
   //[/user-GetStateInformationForVariations]
}


//-------------------------------------------------------------------------------
//  public void SetStateInformationForVariations(byte[] stateInfo)

//  Gets called when a user loads a variation with this module in it.
//  The stateInfo parameter will contain whatever custom data you stored in GetStateInformationForVariations().
//-------------------------------------------------------------------------------
@Override
public void SetStateInformationForVariations(byte[] stateInfo)
{
   //[user-SetStateInformationForVariations]   Add your own code here
   SetStateInformation(stateInfo);



   //[/user-SetStateInformationForVariations]
}


// Auto-generated variables
private VoltageLabel textLabel13;
private VoltageToggle toggleSamples;
private VoltageLabel labelDebug;
private VoltageLabel labelToggle;
private VoltageToggle toggleDistort;
private VoltageLabel textLabel10;
private VoltageLabel textLabel9;
private VoltageLabel labelDistortion;
private VoltageLabel textLabel7;
private VoltageLabel labelOR;
private VoltageLabel textLabel5;
private VoltageLabel labelOL;
private VoltageLabel labelIR;
private VoltageLabel labelIL;
private VoltageLabel labelInput;
private VoltageAudioJack outputR;
private VoltageAudioJack outputL;
private VoltageAudioJack inputR;
private VoltageAudioJack inputL;
private VoltageKnob knobDistortion;
private VoltageKnob knobResolution;
private VoltageKnob knobSamples;


//[user-code-and-variables]    Add your own variables and functions here

private double distortionAmount;
private double samplesAmount;
private double resolutionAmount;
private boolean distortAfter;
private boolean samplesAfter;

//[/user-code-and-variables]
}

 