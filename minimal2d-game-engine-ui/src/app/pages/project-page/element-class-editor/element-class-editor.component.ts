import {Component, Input} from '@angular/core';
import {NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ElementClass} from "../../../models/elementclass";

@Component({
  selector: 'app-element-class-editor',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './element-class-editor.component.html',
  styleUrl: './element-class-editor.component.css'
})
export class ElementClassEditorComponent {

  @Input() elementClass!: ElementClass;

}
