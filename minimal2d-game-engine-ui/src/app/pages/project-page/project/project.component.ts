import {Component, EventEmitter, Output} from '@angular/core';
import {Router} from "@angular/router";
import {Project} from "../../../models/project";
import {NgIf, TitleCasePipe} from "@angular/common";
import {ProjectService} from "../../../services/project.service";
import {SidebarComponent} from "../sidebar/sidebar.component";
import {ProjectResources} from "../../../models/projectResources";

@Component({
  selector: 'app-project',
  standalone: true,
  imports: [NgIf, SidebarComponent, TitleCasePipe],
  templateUrl: './project.component.html',
  styleUrl: './project.component.css'
})
export class ProjectComponent {

  project!: Project;
  projectResources!: ProjectResources;

  constructor(private router: Router, private projectService: ProjectService) {
    let navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state) {
      this.project = navigation.extras.state['project'];
      this.projectService.retrieveProjectResources(this.project).subscribe(projectResource => {
        this.projectResources = projectResource;
      });

    }
  }
}
