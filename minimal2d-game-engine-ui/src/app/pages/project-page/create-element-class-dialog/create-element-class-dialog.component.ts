import {Component, inject} from '@angular/core';
import {
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from "@angular/material/dialog";
import {ElementClass} from "../../../models/elementclass";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-create-element-class-dialog',
  standalone: true,
  imports: [
    MatButton,
    MatDialogActions,
    MatDialogContent,
    MatDialogTitle,
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './create-element-class-dialog.component.html',
  styleUrl: './create-element-class-dialog.component.css'
})
export class CreateElementClassDialogComponent {

  representation: string = "";
  className: string = "";
  readonly dialogRef = inject(MatDialogRef<CreateElementClassDialogComponent>);


  onEnter() {
    if (this.className === "" || this.representation === "") {
      this.dialogRef.close()
    } else {
      this.dialogRef.close(new ElementClass(this.representation, this.className, ""));
    }
  }

}
