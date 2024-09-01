import {Component, OnDestroy} from '@angular/core';
import {Router} from "@angular/router";
import {Project} from "../../../models/project";
import {NgIf, TitleCasePipe} from "@angular/common";
import {ProjectService} from "../../../services/project.service";
import {SidebarComponent} from "../sidebar/sidebar.component";
import {ProjectResources} from "../../../models/projectResources";
import {LevelEditorComponent} from "../level-editor/level-editor.component";
import {Subscription} from "rxjs";
import {ProjectStateService} from "../../../state/project-state.service";
import {ResourcesTypes, SelectedEvent} from "../../../state/SelectedEvent";
import {ElementClassEditorComponent} from "../element-class-editor/element-class-editor.component";

@Component({
  selector: 'app-project',
  standalone: true,
  imports: [NgIf, SidebarComponent, TitleCasePipe, LevelEditorComponent, ElementClassEditorComponent],
  templateUrl: './project.component.html',
  styleUrl: './project.component.css'
})
export class ProjectComponent implements OnDestroy {

  project!: Project;
  projectResources!: ProjectResources;
  private readonly subscription: Subscription;
  selectedEvent!: SelectedEvent;

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

  protected readonly ResourcesTypes = ResourcesTypes;
}
