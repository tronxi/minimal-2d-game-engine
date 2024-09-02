import {Component, inject, Input} from '@angular/core';
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {Level} from "../../../models/level";
import {NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {MatFabAnchor} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {Project} from "../../../models/project";
import {ProjectService} from "../../../services/project.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CodeEditor} from "@acrodata/code-editor";

@Component({
  selector: 'app-level-editor',
  standalone: true,
  imports: [MatLabel, MatFormField, MatInput, NgIf, FormsModule, MatFabAnchor, MatIcon, CodeEditor],
  templateUrl: './level-editor.component.html',
  styleUrl: './level-editor.component.css'
})
export class LevelEditorComponent {

  @Input() level!: Level;
  @Input() project!: Project;
  private snackBar = inject(MatSnackBar);

  constructor(private projectService: ProjectService) {

  }

  onClickSaveLevel() {
    this.projectService.addLevel(this.project, this.level).subscribe({
      next: _ => {
        this.snackBar.open("Level saved successfully!", "", {
          duration: 3000
        });
      }, error: _ => {
        this.snackBar.open("Error saving level", "", {
          duration: 5000
        });
      }
    });
  }
}
