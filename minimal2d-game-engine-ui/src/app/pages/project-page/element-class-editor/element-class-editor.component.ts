import {Component, inject, Input} from '@angular/core';
import {NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ElementClass} from "../../../models/elementclass";
import {MatFabAnchor} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {Project} from "../../../models/project";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ProjectService} from "../../../services/project.service";
import {CodeEditor} from "@acrodata/code-editor";
import {languages} from '@codemirror/language-data';

@Component({
  selector: 'app-element-class-editor',
  standalone: true,
  imports: [NgIf, ReactiveFormsModule, FormsModule, MatFabAnchor, MatIcon, CodeEditor],
  templateUrl: './element-class-editor.component.html',
  styleUrl: './element-class-editor.component.css'
})
export class ElementClassEditorComponent {
  protected readonly languages = languages;

  @Input() elementClass!: ElementClass;
  @Input() project!: Project;
  private snackBar = inject(MatSnackBar);

  constructor(private projectService: ProjectService) {
  }


  onClickSaveElement() {
    this.projectService.addElementClass(this.project, this.elementClass).subscribe({
      next: _ => {
        this.snackBar.open("ElementClass saved successfully!", "", {
          duration: 3000
        });
      }, error: _ => {
        this.snackBar.open("Error saving ElementClass", "", {
          duration: 5000
        });
      }
    });
  }

}
