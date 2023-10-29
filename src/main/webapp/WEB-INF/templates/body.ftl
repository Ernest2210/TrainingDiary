<#include "base.ftl">

<#macro page_title>Замеры тела</#macro>

<#macro page_body>
    <div class="row">
        <div class="col">
            <canvas id="weight"></canvas>
        </div>
        <div class="col">
            <canvas id="height"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <canvas id="shoulders"></canvas>
        </div>
        <div class="col">
            <canvas id="breast"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <canvas id="waist"></canvas>
        </div>
        <div class="col">
            <canvas id="pelvis"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <canvas id="forearm"></canvas>
        </div>
        <div class="col">
            <canvas id="wrist"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col">
            <canvas id="hip"></canvas>
        </div>
        <div class="col">
            <canvas id="shin"></canvas>
        </div>
    </div>

    <script src="
https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js
"></script>
    <script>
        let date = []
        let value = []
        <#list weightList as item>
            date.push("${item.getStringDate()}")
            value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Вес",
                        data: value
                    }
                ]
            }
        }
        weightChart = new Chart(document.querySelector("#weight").getContext('2d'), context)

        date = []
        value = []
        <#list heightList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Рост",
                        data: value
                    }
                ]
            }
        }
        let heightChart = new Chart(document.querySelector("#height").getContext('2d'), context)

        date = []
        value = []
        <#list shouldersList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват плеч",
                        data: value
                    }
                ]
            }
        }
        let shouldersChart = new Chart(document.querySelector("#shoulders").getContext('2d'), context)

        date = []
        value = []
        <#list breastList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват груди",
                        data: value
                    }
                ]
            }
        }
        let breastChart = new Chart(document.querySelector("#breast").getContext('2d'), context)

        date = []
        value = []
        <#list waistList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват талии",
                        data: value
                    }
                ]
            }
        }
        let waistChart = new Chart(document.querySelector("#waist").getContext('2d'), context)

        date = []
        value = []
        <#list pelvisList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват таза",
                        data: value
                    }
                ]
            }
        }
        let pelvisChart = new Chart(document.querySelector("#pelvis").getContext('2d'), context)

        date = []
        value = []
        <#list forearmList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват предплечья",
                        data: value
                    }
                ]
            }
        }
        let forearmChart = new Chart(document.querySelector("#forearm").getContext('2d'), context)

        date = []
        value = []
        <#list wristList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват запястья",
                        data: value
                    }
                ]
            }
        }
        let wristChart = new Chart(document.querySelector("#wrist").getContext('2d'), context)

        date = []
        value = []
        <#list hipList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват бедра",
                        data: value
                    }
                ]
            }
        }
        let hipChart = new Chart(document.querySelector("#hip").getContext('2d'), context)

        date = []
        value = []
        <#list shinList as item>
        date.push("${item.getStringDate()}")
        value.push(${item.getValue()})
        </#list>
        context = {
            type: "line",
            data: {
                labels: date,
                datasets: [
                    {
                        label: "Обхват голени",
                        data: value
                    }
                ]
            }
        }
        let shinChart = new Chart(document.querySelector("#shin").getContext('2d'), context)

    </script>
</#macro>

<@display_page/>