import {Component, inject} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent, MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Level} from "../../../models/level";

@Component({
  selector: 'app-create-level-dialog',
  standalone: true,
  imports: [MatButton, MatDialogActions, MatDialogContent, MatDialogTitle, MatFormField, MatInput, MatLabel, ReactiveFormsModule, MatDialogClose, FormsModule],
  templateUrl: './create-level-dialog.component.html',
  styleUrl: './create-level-dialog.component.css'
})
export class CreateLevelDialogComponent {

  levelName: string = "";
  readonly dialogRef = inject(MatDialogRef<CreateLevelDialogComponent>);

  onEnter() {
    if(this.levelName === "") {
      this.dialogRef.close()
    } else {
      this.dialogRef.close(new Level(this.levelName, ""));
    }
  }

}
