import {Component, inject} from '@angular/core';
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatButton} from "@angular/material/button";
import {FormsModule} from "@angular/forms";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-create-project-dialog',
  standalone: true,
  imports: [MatDialogContent, MatFormField, MatDialogActions, MatDialogClose, MatButton, FormsModule, MatInput, MatDialogTitle, MatLabel],
  templateUrl: './create-project-dialog.component.html',
  styleUrl: './create-project-dialog.component.css'
})
export class CreateProjectDialogComponent {
  projectName: string = "";
  readonly dialogRef = inject(MatDialogRef<CreateProjectDialogComponent>);


  onEnter() {
    this.dialogRef.close(this.projectName);
  }
}
