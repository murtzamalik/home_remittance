import React, { useMemo } from "react";
import { Chart } from "primereact/chart";
import { useTranslation } from "react-i18next";
import "./Dashboard.css";

const TEAL = "#7ACBC7";
const TEAL_DEEP = "#5FB3AF";
const TEAL_INK = "#2F7F7B";
const SUCCESS = "#1F9D7A";
const WARN = "#D4A017";
const DANGER = "#D64545";
const INFO = "#3B82A0";
const MUTED = "#94A3B8";

/** Dummy dataset shaped like batchInquiry / processTransactions / mt102Inquiry outputs */
const DUMMY = {
    kpis: {
        totalRecords: 18420,
        paidAmount: 487650000,
        remainingAmount: 62340000,
        successfulQty: 15210,
        bankTransferQty: 1840,
        validationFailQty: 620,
    },
    statusDistribution: [
        { statusCode: "S", statusDescr: "TRANSACTION SUCCESSFULL", qty: 15210 },
        { statusCode: "BT", statusDescr: "BANK TRANSFER", qty: 1840 },
        { statusCode: "V", statusDescr: "NACTA/AML VALIDATION SUCCESSFULL", qty: 420 },
        { statusCode: "N", statusDescr: "NEW", qty: 310 },
        { statusCode: "VBC", statusDescr: "VALIDATION FAILED DUE TO BENEFICIARY CNIC", qty: 280 },
        { statusCode: "D", statusDescr: "DUPLICATE", qty: 190 },
        { statusCode: "BTE", statusDescr: "BT ERROR", qty: 95 },
        { statusCode: "FTU", statusDescr: "ERROR ON INTER FUNDS TRANSFER", qty: 75 },
    ],
    channelMix: [
        { channel: "File", apiType: "A", totalRecords: 11250, totalAmount: 312000000 },
        { channel: "Push API", apiType: "PUSH", totalRecords: 4860, totalAmount: 158000000 },
        { channel: "Pull API", apiType: "PULL", totalRecords: 2310, totalAmount: 80050000 },
    ],
    dailyVolume: [
        { day: "Mon", totalRecords: 2410, paidAmount: 62000000 },
        { day: "Tue", totalRecords: 2680, paidAmount: 71200000 },
        { day: "Wed", totalRecords: 2550, paidAmount: 68800000 },
        { day: "Thu", totalRecords: 2910, paidAmount: 75400000 },
        { day: "Fri", totalRecords: 3120, paidAmount: 82100000 },
        { day: "Sat", totalRecords: 2380, paidAmount: 64100000 },
        { day: "Sun", totalRecords: 2370, paidAmount: 64050000 },
    ],
    processRun: {
        totalTransactions: 2500,
        sucessfulTransactions: 2180,
        unsucessfullIFT: 145,
        unSucessfullIBFT: 175,
    },
    remitterCompanies: [
        { finInstDescr: "Western Union", totalAmount: 128500000, totalRecords: 4200 },
        { finInstDescr: "MoneyGram", totalAmount: 96500000, totalRecords: 3180 },
        { finInstDescr: "Ria Money Transfer", totalAmount: 74200000, totalRecords: 2650 },
        { finInstDescr: "Xpress Money", totalAmount: 58100000, totalRecords: 1980 },
        { finInstDescr: "WorldRemit", totalAmount: 43900000, totalRecords: 1520 },
    ],
    mt102ByBank: [
        { bankName: "HBL", noOfTrans: 420, amount: 18500000 },
        { bankName: "UBL", noOfTrans: 380, amount: 16200000 },
        { bankName: "MCB", noOfTrans: 310, amount: 14100000 },
        { bankName: "Allied Bank", noOfTrans: 290, amount: 12800000 },
        { bankName: "Meezan Bank", noOfTrans: 250, amount: 11200000 },
    ],
};

const formatPkr = (value) =>
    new Intl.NumberFormat("en-PK", {
        style: "currency",
        currency: "PKR",
        maximumFractionDigits: 0,
    }).format(value);

const formatNumber = (value) => new Intl.NumberFormat("en-PK").format(value);

function Dashboard() {
    const { t } = useTranslation();

    const statusChart = useMemo(() => {
        const labels = DUMMY.statusDistribution.map((s) => s.statusCode);
        const data = DUMMY.statusDistribution.map((s) => s.qty);
        return {
            data: {
                labels,
                datasets: [
                    {
                        data,
                        backgroundColor: [SUCCESS, TEAL, TEAL_DEEP, INFO, DANGER, WARN, MUTED, "#E07A5F"],
                        hoverBackgroundColor: [SUCCESS, TEAL, TEAL_DEEP, INFO, DANGER, WARN, MUTED, "#E07A5F"],
                        borderWidth: 0,
                    },
                ],
            },
            options: {
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: "right",
                        labels: {
                            usePointStyle: true,
                            generateLabels: (chart) => {
                                const ds = chart.data.datasets[0];
                                return chart.data.labels.map((label, i) => {
                                    const row = DUMMY.statusDistribution[i];
                                    return {
                                        text: `${label} — ${row.statusDescr} (${formatNumber(row.qty)})`,
                                        fillStyle: ds.backgroundColor[i],
                                        strokeStyle: ds.backgroundColor[i],
                                        hidden: false,
                                        index: i,
                                    };
                                });
                            },
                        },
                    },
                    tooltip: {
                        callbacks: {
                            label: (ctx) => {
                                const row = DUMMY.statusDistribution[ctx.dataIndex];
                                return `${row.statusDescr}: ${formatNumber(row.qty)}`;
                            },
                        },
                    },
                },
            },
        };
    }, []);

    const channelChart = useMemo(() => {
        return {
            data: {
                labels: DUMMY.channelMix.map((c) => c.channel),
                datasets: [
                    {
                        label: "Transactions",
                        backgroundColor: TEAL,
                        data: DUMMY.channelMix.map((c) => c.totalRecords),
                        borderRadius: 6,
                    },
                    {
                        label: "Amount (PKR lac)",
                        backgroundColor: TEAL_INK,
                        data: DUMMY.channelMix.map((c) => Math.round(c.totalAmount / 100000)),
                        borderRadius: 6,
                    },
                ],
            },
            options: {
                maintainAspectRatio: false,
                plugins: {
                    legend: { position: "top" },
                },
                scales: {
                    x: { grid: { display: false } },
                    y: { beginAtZero: true, ticks: { precision: 0 } },
                },
            },
        };
    }, []);

    const dailyChart = useMemo(() => {
        return {
            data: {
                labels: DUMMY.dailyVolume.map((d) => d.day),
                datasets: [
                    {
                        label: "Paid amount (PKR M)",
                        data: DUMMY.dailyVolume.map((d) => Math.round(d.paidAmount / 1000000)),
                        fill: true,
                        borderColor: TEAL_INK,
                        backgroundColor: "rgba(122, 203, 199, 0.25)",
                        tension: 0.35,
                        pointBackgroundColor: TEAL,
                        pointBorderColor: TEAL_INK,
                    },
                    {
                        label: "Txn count",
                        data: DUMMY.dailyVolume.map((d) => d.totalRecords),
                        fill: false,
                        borderColor: INFO,
                        backgroundColor: INFO,
                        tension: 0.35,
                        yAxisID: "y1",
                    },
                ],
            },
            options: {
                maintainAspectRatio: false,
                interaction: { mode: "index", intersect: false },
                plugins: { legend: { position: "top" } },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: { display: true, text: "PKR millions" },
                    },
                    y1: {
                        beginAtZero: true,
                        position: "right",
                        grid: { drawOnChartArea: false },
                        title: { display: true, text: "Transactions" },
                    },
                },
            },
        };
    }, []);

    const processChart = useMemo(() => {
        const p = DUMMY.processRun;
        return {
            data: {
                labels: ["Successful", "IFT Fail (FTU/FTT)", "IBFT Fail (BT path)"],
                datasets: [
                    {
                        label: "Last process run",
                        backgroundColor: [SUCCESS, DANGER, WARN],
                        data: [Number(p.sucessfulTransactions), Number(p.unsucessfullIFT), Number(p.unSucessfullIBFT)],
                        borderRadius: 8,
                    },
                ],
            },
            options: {
                indexAxis: "y",
                maintainAspectRatio: false,
                plugins: { legend: { display: false } },
                scales: {
                    x: { beginAtZero: true, ticks: { precision: 0 } },
                    y: { grid: { display: false } },
                },
            },
        };
    }, []);

    const remitterChart = useMemo(() => {
        return {
            data: {
                labels: DUMMY.remitterCompanies.map((r) => r.finInstDescr),
                datasets: [
                    {
                        label: "Total amount (PKR M)",
                        backgroundColor: TEAL_DEEP,
                        data: DUMMY.remitterCompanies.map((r) => Math.round(r.totalAmount / 1000000)),
                        borderRadius: 6,
                    },
                ],
            },
            options: {
                indexAxis: "y",
                maintainAspectRatio: false,
                plugins: { legend: { display: false } },
                scales: {
                    x: { beginAtZero: true },
                    y: { grid: { display: false } },
                },
            },
        };
    }, []);

    const mt102Chart = useMemo(() => {
        return {
            data: {
                labels: DUMMY.mt102ByBank.map((b) => b.bankName),
                datasets: [
                    {
                        label: "No. of trans",
                        backgroundColor: TEAL,
                        data: DUMMY.mt102ByBank.map((b) => b.noOfTrans),
                        borderRadius: 6,
                    },
                    {
                        label: "Amount (PKR M)",
                        backgroundColor: TEAL_INK,
                        data: DUMMY.mt102ByBank.map((b) => Math.round(b.amount / 1000000)),
                        borderRadius: 6,
                    },
                ],
            },
            options: {
                maintainAspectRatio: false,
                plugins: { legend: { position: "top" } },
                scales: {
                    x: { grid: { display: false } },
                    y: { beginAtZero: true },
                },
            },
        };
    }, []);

    const { kpis } = DUMMY;

    return (
        <div className="remit-dashboard">
            <h2 className="remit-dashboard__title">{t("Welcome to Home Remittance.")}</h2>
            <p className="remit-dashboard__subtitle">Operations overview — volumes, status funnel, channels, and settlement pipeline</p>

            <div className="remit-kpi-grid">
                <div className="remit-kpi remit-kpi--teal">
                    <div className="remit-kpi__label">Total transactions</div>
                    <div className="remit-kpi__value">{formatNumber(kpis.totalRecords)}</div>
                    <div className="remit-kpi__hint">batchInquiry · totalRecords</div>
                </div>
                <div className="remit-kpi remit-kpi--success">
                    <div className="remit-kpi__label">Paid amount</div>
                    <div className="remit-kpi__value">{formatPkr(kpis.paidAmount)}</div>
                    <div className="remit-kpi__hint">STATUS = S · paidAmount</div>
                </div>
                <div className="remit-kpi remit-kpi--warn">
                    <div className="remit-kpi__label">Remaining amount</div>
                    <div className="remit-kpi__value">{formatPkr(kpis.remainingAmount)}</div>
                    <div className="remit-kpi__hint">Unsettled · remainingAmount</div>
                </div>
                <div className="remit-kpi remit-kpi--danger">
                    <div className="remit-kpi__label">Validation fails</div>
                    <div className="remit-kpi__value">{formatNumber(kpis.validationFailQty)}</div>
                    <div className="remit-kpi__hint">VBC / VBN / VRN / D</div>
                </div>
            </div>

            <div className="remit-chart-grid">
                <div className="remit-chart-card">
                    <div className="remit-chart-card__header">
                        <h3 className="remit-chart-card__title">Status distribution</h3>
                        <p className="remit-chart-card__desc">transStatusCounts from batchInquiry (LKP_STATUS codes)</p>
                    </div>
                    <div className="remit-chart-wrap remit-chart-wrap--tall">
                        <Chart type="doughnut" data={statusChart.data} options={statusChart.options} style={{ width: "100%", height: "100%" }} />
                    </div>
                </div>

                <div className="remit-chart-card">
                    <div className="remit-chart-card__header">
                        <h3 className="remit-chart-card__title">Ingest channel mix</h3>
                        <p className="remit-chart-card__desc">File (A) · Push API · Pull API</p>
                    </div>
                    <div className="remit-chart-wrap">
                        <Chart type="bar" data={channelChart.data} options={channelChart.options} style={{ width: "100%", height: "100%" }} />
                    </div>
                </div>

                <div className="remit-chart-card remit-chart-card--wide">
                    <div className="remit-chart-card__header">
                        <h3 className="remit-chart-card__title">Daily remittance volume</h3>
                        <p className="remit-chart-card__desc">Paid amount vs transaction count (last 7 days)</p>
                    </div>
                    <div className="remit-chart-wrap">
                        <Chart type="line" data={dailyChart.data} options={dailyChart.options} style={{ width: "100%", height: "100%" }} />
                    </div>
                </div>

                <div className="remit-chart-card">
                    <div className="remit-chart-card__header">
                        <h3 className="remit-chart-card__title">Process run quality</h3>
                        <p className="remit-chart-card__desc">processTransactions — success / IFT / IBFT fails</p>
                    </div>
                    <div className="remit-chart-wrap">
                        <Chart type="bar" data={processChart.data} options={processChart.options} style={{ width: "100%", height: "100%" }} />
                    </div>
                </div>

                <div className="remit-chart-card">
                    <div className="remit-chart-card__header">
                        <h3 className="remit-chart-card__title">Top remitting companies</h3>
                        <p className="remit-chart-card__desc">finInstDescr · totalAmount from batchInquiry</p>
                    </div>
                    <div className="remit-chart-wrap">
                        <Chart type="bar" data={remitterChart.data} options={remitterChart.options} style={{ width: "100%", height: "100%" }} />
                    </div>
                </div>

                <div className="remit-chart-card remit-chart-card--wide">
                    <div className="remit-chart-card__header">
                        <h3 className="remit-chart-card__title">MT102 by beneficiary bank</h3>
                        <p className="remit-chart-card__desc">mt102Inquiry — noOfTrans & amount by bankName</p>
                    </div>
                    <div className="remit-chart-wrap">
                        <Chart type="bar" data={mt102Chart.data} options={mt102Chart.options} style={{ width: "100%", height: "100%" }} />
                    </div>
                </div>
            </div>

            <p className="remit-dashboard__note">Sample operations data for demo — mirrors batchInquiry, processTransactions, and mt102Inquiry response shapes.</p>
        </div>
    );
}

export default Dashboard;
