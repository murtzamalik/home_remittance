import React from 'react';
import MultipleFileUpload from '../../../components/multipleFileUpload';

const OverseaBulkData = () => {
    const handleUpload = (files) => {
    }

    
    
  return (
    <div>
        <MultipleFileUpload handleUpload={handleUpload} heading="Oversea Bulk Data" isMulti={true} isConvert64={true} />
    </div>
  )
}

export default OverseaBulkData;