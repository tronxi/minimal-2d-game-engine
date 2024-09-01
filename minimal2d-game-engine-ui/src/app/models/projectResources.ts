import {Project} from "./project";
import {Level} from "./level";
import {ElementClass} from "./elementclass";

export class ProjectResources {
  project: Project;
  levels: Level[];
  elementClasses: ElementClass[];

  constructor(project: Project, levels: Level[], elementClasses: ElementClass[]) {
    this.project = project;
    this.levels = levels;
    this.elementClasses = elementClasses;
  }
}
