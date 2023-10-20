<#include "base.ftl">
<#macro page_title>Профиль</#macro>

<#macro page_body>
    <div class="container rounded bg-white mt-5 mb-5">
        <div class="row">
            <div class="col-md">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Профиль</h4>
                    </div>
                    <form action="./profile" method="POST">
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">Имя</label><input id="profile-name-input" type="text" name="name" class="form-control" placeholder="first name" value="${user.getName()}"></div>
                            <input type="hidden" name="old_name" value="${user.getName()}">
                            <div class="col-md-6"><label class="labels">Фамилия</label><input id="profile-surname-input" type="text" name="surname" class="form-control" value="${user.getSurname()}" placeholder="surname"></div>
                            <input type="hidden" name="old_surname" value="${user.getSurname()}">
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12"><label class="labels">Логин</label><input id="profile-login-input" disabled type="text" class="form-control" value="${user.getLogin()}"></div>
                            <div class="col-md-12"><label class="labels">День рождения</label><input id="profile-birthday-input" type="date" name="birthday" class="form-control" value="${user.getBirthdayString()}"></div>
                            <input type="hidden" name="old_birthday" value="${user.getBirthdayString()}">
                        </div>
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Параметры тела</h4>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">Рост</label><input id="profile-height-input" type="number" name="height" class="form-control" value="<#if height??>${height.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_height" value="<#if height??>${height.getValue()}<#else>0</#if>">
                            <div class="col-md-6"><label class="labels">Вес</label><input id="profile-weight-input" type="number" name="weight" class="form-control" value="<#if weight??>${weight.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_weight" value="<#if weight??>${weight.getValue()}<#else>0</#if>">
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">Охват плеч</label><input id="profile-shoulders-input" type="number" name="shoulders" class="form-control" value="<#if shoulders??>${shoulders.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_shoulders" value="<#if shoulders??>${shoulders.getValue()}<#else>0</#if>">
                            <div class="col-md-6"><label class="labels">Охват груди</label><input id="profile-breast-input" type="number" name="breast" class="form-control" value="<#if breast??>${breast.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_breast" value="<#if breast??>${breast.getValue()}<#else>0</#if>">
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">Охват талии</label><input id="profile-waist-input" type="number" name="waist" class="form-control" value="<#if waist??>${waist.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_waist" value="<#if waist??>${waist.getValue()}<#else>0</#if>">
                            <div class="col-md-6"><label class="labels">Охват таза</label><input id="profile-pelvis-input" type="number" name="pelvis" class="form-control" value="<#if pelvis??>${pelvis.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_pelvis" value="<#if pelvis??>${pelvis.getValue()}<#else>0</#if>">
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">Охват предплечья</label><input id="profile-forearm-input" type="number" name="forearm" class="form-control" value="<#if forearm??>${forearm.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_forearm" value="<#if forearm??>${forearm.getValue()}<#else>0</#if>">
                            <div class="col-md-6"><label class="labels">Охват запястья</label><input id="profile-wrist-input" type="number" name="wrist" class="form-control" value="<#if wrist??>${wrist.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_wrist" value="<#if wrist??>${wrist.getValue()}<#else>0</#if>">
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6"><label class="labels">Охват бедра</label><input id="profile-hip-input" type="number" name="hip" class="form-control" value="<#if hip??>${hip.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_hip" value="<#if hip??>${hip.getValue()}<#else>0</#if>">
                            <div class="col-md-6"><label class="labels">Охват голени</label><input id="profile-shin-input" type="number" name="shin" class="form-control" value="<#if shin??>${shin.getValue()}<#else>0</#if>"></div>
                            <input type="hidden" name="old_shin" value="<#if shin??>${shin.getValue()}<#else>0</#if>">
                        </div>
                        <div class="mt-5 text-center"><button id="profile-save-button" class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
    </div>
</#macro>

<@display_page/>