import {Level} from "../models/level";
import {ElementClass} from "../models/elementclass";

export enum ResourcesTypes {
  Level, ElementClass
}

export class SelectedEvent {
  type: ResourcesTypes;
  level?: Level;
  elementClass?: ElementClass;

  private constructor(resourceType: ResourcesTypes, level?: Level, elementClass?: ElementClass) {
    this.type = resourceType;
    this.level = level;
    this.elementClass = elementClass;
  }

  static fromLevel(level: Level): SelectedEvent {
    return new SelectedEvent(ResourcesTypes.Level, level, undefined);
  }

  static fromElementClass(elementClass: ElementClass): SelectedEvent {
    return new SelectedEvent(ResourcesTypes.ElementClass, undefined, elementClass);
  }
}
