import {Component, Input} from '@angular/core';
import {Project} from "../../../models/project";
import {MatCard, MatCardContent, MatCardFooter, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatIcon} from "@angular/material/icon";
import {Router} from "@angular/router";
import {ProjectService} from "../../../services/project.service";
import {WorkspaceStateService} from "../../../state/workspace-state.service";

@Component({
  selector: 'app-workspace-project',
  standalone: true,
  imports: [MatCardContent, MatCard, MatIcon, MatCardHeader, MatCardTitle, MatCardFooter],
  templateUrl: './workspace-project.component.html',
  styleUrl: './workspace-project.component.css'
})
export class WorkspaceProjectComponent {
  @Input() project!: Project;

  constructor(private router: Router,
              private projectService: ProjectService,
              private workspaceStateService: WorkspaceStateService,) {
  }

  onClickProject(): void {
    this.router.navigate(['/project'], {state: {project: this.project}});
  }

  onClickDeleteProject(event: Event) {
    event.stopPropagation();
    this.projectService.delete(this.project).subscribe({
      next: _ => {
        this.workspaceStateService.update();
      }
    })
  }
}
