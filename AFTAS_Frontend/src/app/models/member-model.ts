import {IdentityDocType} from "../enum/identityDocType";


export interface MemberModel{
   num?:number,
   name:string,
  familyName:string,
  accessionDate:Date,
  nationality:string,
  identityDocumentType:IdentityDocType,
  identityNumber:string
}
