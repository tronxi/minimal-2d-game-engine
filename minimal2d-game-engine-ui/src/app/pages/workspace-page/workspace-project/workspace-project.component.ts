import {Component, Input} from '@angular/core';
import {Project} from "../../../models/project";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {TitleCasePipe} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-workspace-project',
  standalone: true,
  imports: [MatCardContent, MatCard, MatIcon, MatCardHeader, MatCardTitle, TitleCasePipe],
  templateUrl: './workspace-project.component.html',
  styleUrl: './workspace-project.component.css'
})
export class WorkspaceProjectComponent {
  @Input() project!: Project;

  constructor(private router: Router) {
  }

  onClickProject(): void {
    this.router.navigate(['/project'], {state: {project: this.project}});
  }

}
