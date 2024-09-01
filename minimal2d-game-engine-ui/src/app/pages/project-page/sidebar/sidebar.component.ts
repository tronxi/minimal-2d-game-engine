import {Component, inject, Input, OnChanges, SimpleChanges} from '@angular/core';
import {MatTab, MatTabGroup} from "@angular/material/tabs";
import {ProjectResources} from "../../../models/projectResources";
import {NgForOf, NgIf} from "@angular/common";
import {Level} from "../../../models/level";
import {ElementClass} from "../../../models/elementclass";
import {ProjectStateService} from "../../../state/project-state.service";
import {SelectedEvent} from "../../../state/SelectedEvent";
import {MatFabAnchor} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatDialog} from "@angular/material/dialog";
import {CreateLevelDialogComponent} from "../create-level-dialog/create-level-dialog.component";
import {ProjectService} from "../../../services/project.service";

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [MatTabGroup, MatTab, NgForOf, MatFabAnchor, NgIf, MatIcon],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnChanges {
  readonly dialog = inject(MatDialog);

  @Input() projectResources!: ProjectResources;

  constructor(private projectStateService: ProjectStateService, private projectService: ProjectService) {
  }

  ngOnChanges(changes: SimpleChanges) {
    this.projectResources = changes['projectResources'].currentValue;
  }

  onClickLevel(level: Level) {
    this.projectStateService.update(SelectedEvent.fromLevel(level));
  }

  onClickElementClass(elementClass: ElementClass) {
    this.projectStateService.update(SelectedEvent.fromElementClass(elementClass));
  }

  onClickAddLevel() {
    const dialogRef = this.dialog.open(CreateLevelDialogComponent, {});

    dialogRef.afterClosed().subscribe(level => {
      if (level !== undefined) {
        this.projectService.addLevel(this.projectResources.project, level).subscribe(_ => {
          this.projectService.retrieveProjectResources(this.projectResources.project).subscribe(value => {
            this.projectResources = value;
          })
        });
      }
    });

  }

  onClickAddElement() {
    console.log("add element")
  }
}
