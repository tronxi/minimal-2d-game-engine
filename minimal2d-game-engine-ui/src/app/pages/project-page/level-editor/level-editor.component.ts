import {Component, Input} from '@angular/core';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {Level} from "../../../models/level";
import {NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-level-editor',
  standalone: true,
  imports: [MatLabel, MatFormField, MatInput, NgIf, FormsModule],
  templateUrl: './level-editor.component.html',
  styleUrl: './level-editor.component.css'
})
export class LevelEditorComponent {

  @Input() level!: Level;

  constructor() {

  }

}
