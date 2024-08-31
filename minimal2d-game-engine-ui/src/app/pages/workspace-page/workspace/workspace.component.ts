import {Component, inject, OnInit} from '@angular/core';
import {ProjectService} from "../../../services/project.service";
import {WorkspaceProjectComponent} from "../workspace-project/workspace-project.component";
import {Project} from "../../../models/project";
import {NgForOf} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {MatFabAnchor} from "@angular/material/button";
import {MatDialog} from "@angular/material/dialog";
import {
  CreateProjectDialogComponent
} from "../create-project-dialog/create-project-dialog.component";

@Component({
  selector: 'app-workspace',
  standalone: true,
  imports: [WorkspaceProjectComponent, NgForOf, MatIcon, MatFabAnchor],
  templateUrl: './workspace.component.html',
  styleUrl: './workspace.component.css'
})
export class WorkspaceComponent implements OnInit {
  readonly dialog = inject(MatDialog);

  projects: Project[] = [];

  constructor(private projectService: ProjectService) {
  }

  ngOnInit(): void {
    this.retrieveProjects();
  }

  onClickCreateProject(): void {
    const dialogRef = this.dialog.open(CreateProjectDialogComponent, {});

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.projectService.createProject(new Project(result)).subscribe(_ => {
          this.retrieveProjects();
        })
      }
    });
  }

  private retrieveProjects(): void {
    this.projectService.retrieveProjects().subscribe(projects => {
      this.projects = projects;
    });
  }

}
