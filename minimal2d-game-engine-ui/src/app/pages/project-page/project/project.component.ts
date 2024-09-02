import {Component, inject, OnDestroy} from '@angular/core';
import {Router} from "@angular/router";
import {Project} from "../../../models/project";
import {NgClass, NgIf} from "@angular/common";
import {ProjectService} from "../../../services/project.service";
import {SidebarComponent} from "../sidebar/sidebar.component";
import {ProjectResources} from "../../../models/projectResources";
import {LevelEditorComponent} from "../level-editor/level-editor.component";
import {Subscription} from "rxjs";
import {ProjectStateService} from "../../../state/project-state.service";
import {ResourcesTypes, SelectedEvent} from "../../../state/SelectedEvent";
import {ElementClassEditorComponent} from "../element-class-editor/element-class-editor.component";
import {MatFabAnchor} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-project',
  standalone: true,
  imports: [NgIf, SidebarComponent, LevelEditorComponent, ElementClassEditorComponent, MatFabAnchor, MatIcon, NgClass],
  templateUrl: './project.component.html',
  styleUrl: './project.component.css'
})
export class ProjectComponent implements OnDestroy {

  project!: Project;
  projectResources!: ProjectResources;
  private readonly subscription: Subscription;
  selectedEvent!: SelectedEvent;
  protected readonly ResourcesTypes = ResourcesTypes;
  building: boolean = false;
  private snackBar = inject(MatSnackBar);

  constructor(private router: Router, private projectService: ProjectService, private projectStateService: ProjectStateService) {
    let navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state) {
      this.project = navigation.extras.state['project'];
      this.projectService.retrieveProjectResources(this.project).subscribe(projectResource => {
        this.projectResources = projectResource;
      });
    }
    this.subscription = this.projectStateService.state$.subscribe(selectedEvent => {
      this.selectedEvent = selectedEvent;
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  onClickBuildProject() {
    if (this.building) return;

    this.building = true;
    this.projectService.buildProject(this.project).subscribe({
      next: _ => {
        this.building = false;
        this.snackBar.open("The project was built successfully!", "", {
          duration: 3000
        });
      }, error: error => {
        console.error(error.error.message);
        this.building = false;
        this.snackBar.open("Error building jar", "", {
          duration: 5000
        });
      }
    });
  }

  onClickDownloadJar() {
    this.projectService.downloadJar(this.project);
  }
}
