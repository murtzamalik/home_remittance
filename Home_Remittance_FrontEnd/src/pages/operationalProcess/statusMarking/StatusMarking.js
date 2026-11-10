import React, {useState} from 'react';
import { TabView, TabPanel } from 'primereact/tabview';
import StatusDataTable from './statusDataTable/StatusDataTable';
import StatusMarkingForm from './statusMarkingForm/StatusMarkingForm';

const StatusMarking = () => {
    const [activeIndex, setActiveIndex] = useState(0);

  return (
    <div className="card Card__Round">
        <div className="Form__Header">
            <h1>Status Marking - Exchange Companies</h1>
        </div>
        {/* Tab View */}

        <TabView activeIndex={activeIndex} onTabChange={(e) => setActiveIndex(e.index)}>
            <TabPanel header="Outstand">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
            <TabPanel header="Hold">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
            <TabPanel header="Rejected">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
            <TabPanel header="Cancelled">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
            <TabPanel header="Blocked">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
            <TabPanel header="Transfer">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
            <TabPanel header="Process">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
            <TabPanel header="Append">
                <StatusMarkingForm />
                <StatusDataTable />
            </TabPanel>
        </TabView>
 
        {/* Tab View Ends */}
    </div>
  )
}

export default StatusMarking;